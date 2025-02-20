package ch.etmles.payroll.Repositories;

import ch.etmles.payroll.Entities.Department;
import ch.etmles.payroll.Entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository, DepartmentRepository departmentRepository){
        return args->{
            log.info("Preloading " + repository.save(new Employee("Russell", "George", "burglar", "russell.george@gigamail.com")));
            log.info("Preloading " + repository.save(new Employee("Hamilton", "Lewis", "thief", "hamilton.lewis@gigamail.com")));
            log.info("Preloading " + departmentRepository.save(new Department("Compta")));
        };
    }
}
