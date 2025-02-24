package ch.etmles.payroll.Department;

public class DepartmentNotFoundException extends RuntimeException {
    DepartmentNotFoundException(long id) {
        super("Could not find department " + id);
    }
}
