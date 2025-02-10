package ch.etmles.payroll.Controllers.Employee;

public class EmployeeNotFoundException extends RuntimeException{
    EmployeeNotFoundException(long id){
        super("Could not find employee " + id);
    }
}
