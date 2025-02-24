package ch.etmles.payroll.Department;

import ch.etmles.payroll.Employee.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /*
    curl -i localhost:8080/departments
     */
    @GetMapping("/departments")
    List<Department> all() {
        return departmentRepository.findAll();
    }

    /*
    curl -i -X POST localhost:8080/departments ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Compta\"}"
     */
    @PostMapping("/departments")
    Department newEmployee(@RequestBody Department newDepartment) {
        return departmentRepository.save(newDepartment);
    }

    /*
    curl -i localhost:8080/departments/1
     */
    @GetMapping("/departments/{id}")
    Department one(@PathVariable Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/departments/2 ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Comptabilité\"}"
     */
    @PutMapping("/departments/{id}")
    Department replaceDepartment(@RequestBody Department newDepartment, @PathVariable Long id) {
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(newDepartment.getName());
                    return departmentRepository.save(department);
                })
                .orElseGet(() -> {
                    newDepartment.setId(id);
                    return departmentRepository.save(newDepartment);
                });
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/departments/2
    Note : use a department id missing to throw the exception
    */
    @DeleteMapping("/departments/{id}")
    void deleteDepartment(@PathVariable Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Department not found");
        }
    }

    @PatchMapping("/departments/{id}/employee")
    void addEmployeeToDepartment(@PathVariable long departmentId, @RequestBody Employee newEmployee) {
        if (departmentRepository.existsById(departmentId)) {
            departmentRepository.findById(departmentId).map(department -> {
                department.addEmployee(newEmployee);
                return departmentRepository.save(department);
            });
        }
    }
}
