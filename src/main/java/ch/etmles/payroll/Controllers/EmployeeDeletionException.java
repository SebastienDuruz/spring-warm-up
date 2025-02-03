package ch.etmles.payroll.Controllers;

public class EmployeeDeletionException extends RuntimeException{

    EmployeeDeletionException(Long id){
        super("Could not delete employee (not found) " + id);
    }
}
