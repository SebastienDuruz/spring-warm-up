package ch.etmles.payroll.Employee;

import ch.etmles.payroll.Department.Department;
import ch.etmles.payroll.Department.DepartmentRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    EmployeeController(EmployeeRepository repository, EmployeeService employeeService){
        this.employeeRepository = repository;
        this.employeeService = employeeService;
    }

    /* curl sample :
    curl -i localhost:8080/employees
    */
    @GetMapping("/employees")
    List<Employee> all(){
        return employeeRepository.findAll();
    }

    /* curl sample :
    curl -i -X POST localhost:8080/employees ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Russel George\", \"role\": \"gardener\"}"
    */
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee){
        return employeeRepository.save(newEmployee);
    }

    /* curl sample :
    curl -i localhost:8080/employees/1
    */
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/employees/2 ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Samwise Bing\", \"role\": \"peer-to-peer\"}"
     */
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setRole(newEmployee.getRole());
                    employee.setEmail(newEmployee.getEmail());
                    employee.setDepartment(newEmployee.getDepartment());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/employees/2
    Note : use an employee id missing to throw the exception
    */
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
        }else{
            throw new EmployeeDeletionException(id);
        }
    }

    /*
    curl -i -X PATCH http://localhost:8080/employees/2   -H "Content-Type: application/json"  -d "{\"department_id\": 1}"
    curl -H "Content-type:application/json" --data '{"id": 1}' -X PATCH http://localhost:8080/employees/2
     */
    @PatchMapping("/employees/{id}")
    Employee updateEmployeeDepartment(@PathVariable Long id, @RequestBody Department department) {
        if(department == null){
            return employeeService.releaseEmployee(id);
        }
        else {
            return employeeService.hireEmployee(id, department.getId());
        }
    }
}