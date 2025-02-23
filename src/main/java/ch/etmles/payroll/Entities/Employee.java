package ch.etmles.payroll.Entities;

import ch.etmles.payroll.Repositories.DepartmentRepository;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Employee {

    private @Id
    @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String role;

    @Column(unique=true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    public Employee(){}

    public Employee(String firstName, String lastName, String role, String email) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setRole(role);
        this.setEmail(email);
    }

    public Long getID(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Employee employee))
            return false;
        return Objects.equals(this.id, employee.id)
                && Objects.equals(this.firstName, employee.firstName)
                && Objects.equals(this.lastName, employee.lastName)
                && Objects.equals(this.role, employee.role)
                && Objects.equals(this.email, employee.email);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.firstName, this.lastName, this.role);
    }

    @Override
    public String toString(){
        return "Employee{" + "id=" + this.getID() + ","
                + " firstName='" + this.getFirstName() + '\'' + ",  "
                + " lastName='" + this.getLastName() + '\'' + ",  "
                + " email='" + this.getEmail() + '\'' + ",  "
                + ",role='" + this.getRole() + '\'' + '}';
    }
}
