package ch.etmles.payroll.Controllers.Employee;

public class EmployeeDeletionException extends RuntimeException{

    EmployeeDeletionException(Long id){
        super("Could not delete employee (not found) " + id);
    }
}
