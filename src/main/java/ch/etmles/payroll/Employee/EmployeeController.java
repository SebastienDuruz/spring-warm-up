package ch.etmles.payroll.Employee;

import ch.etmles.payroll.Department.Department;
import ch.etmles.payroll.Department.DepartmentNotFoundException;
import ch.etmles.payroll.Department.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    EmployeeController(EmployeeRepository repository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository){
        this.repository = repository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    /* curl sample :
    curl -i localhost:8080/employees
    */
    @GetMapping("/employees")
    List<Employee> all(){
        return repository.findAll();
    }

    /* curl sample :
    curl -i -X POST localhost:8080/employees ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Russel George\", \"role\": \"gardener\"}"
    */
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }

    /* curl sample :
    curl -i localhost:8080/employees/1
    */
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/employees/2 ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Samwise Bing\", \"role\": \"peer-to-peer\"}"
     */
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setRole(newEmployee.getRole());
                    employee.setEmail(newEmployee.getEmail());
                    employee.setDepartment(newEmployee.getDepartment());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/employees/2
    Note : use an employee id missing to throw the exception
    */
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EmployeeDeletionException(id);
        }
    }

    /*
    curl -i -X PATCH http://localhost:8080/employees/2   -H "Content-Type: application/json"  -d "{\"department_id\": 1}"
     */
    @PatchMapping("/employees/{id}")
    Employee updateEmployeeDepartment(@PathVariable Long id, @RequestBody Long departmentId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setDepartment(department);
                    return repository.save(employee);
                }).orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}