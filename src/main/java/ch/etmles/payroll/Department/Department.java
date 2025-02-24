package ch.etmles.payroll.Department;

import ch.etmles.payroll.Employee.Employee;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="department")
public class Department {

    private @Id
    @GeneratedValue Long id;

    private String name;

//    @OneToMany(mappedBy = "department")
//    private List<Employee> employees;

    public Department(String name) {
        setName(name);
    }

    public Department() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Employee> getEmployees() {
//        return employees;
//    }

//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }

//    public void addEmployee(Employee employee) {
//        this.employees.add(employee);
//    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Department department))
            return false;
        return Objects.equals(this.id, department.id)
                && Objects.equals(this.name, department.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString(){
        return "Department{" + "id=" + this.getId()
                + ",name='" + this.getName() + '\'' + '}';
    }
}
