package ch.etmles.payroll.Department;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(long id) {
        super("Could not find department " + id);
    }
}
