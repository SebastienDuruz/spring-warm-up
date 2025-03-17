package ch.etmles.payroll.Employee;

import ch.etmles.payroll.Department.Department;
import ch.etmles.payroll.Department.DepartmentNotFoundException;
import ch.etmles.payroll.Department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Employee hireEmployee(Long employeeId, Long departmentId) {

        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        return employeeRepository.findById(employeeId)
            .map(employee -> {
                employee.setDepartment(department);
                return employeeRepository.save(employee);
            }).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    @Transactional
    public Employee releaseEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId)
            .map(employee -> {
                employee.setDepartment(null);
                return employeeRepository.save(employee);
            }).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }
}
