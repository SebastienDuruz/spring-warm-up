package ch.etmles.payroll;

import ch.etmles.payroll.Department.Department;
import ch.etmles.payroll.Department.DepartmentRepository;
import ch.etmles.payroll.Employee.Employee;
import ch.etmles.payroll.Employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
}
