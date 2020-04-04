package com.ascendingdc.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    public Employee(){}
    public Employee(String name, String first_name,String last_name,String email,String address){
         this.name=name;
         this.first_name=first_name;
         this.last_name=last_name;
         this.email=email;
         this.address=address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name ;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "hired_date")
    private LocalDate hired_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    //mappedBy object in account.java which have JoinColumn
    //CascadeType in here means: when I delete employee, it will be deleted all the records on accounts
    @JsonIgnore
    @OneToMany(mappedBy = "employee",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private Set<Account> account;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="employees_roles",joinColumns = {@JoinColumn(name="employee_id")},inverseJoinColumns = {@JoinColumn(name="role_id")})
    @JsonIgnore
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Set<Account> getAccount() {
        return account;
    }

    public void setAccount(Set<Account> account) {
        this.account = account;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getHired_date() {
        return hired_date;
    }

    public void setHired_date(LocalDate hired_date) {
        this.hired_date = hired_date;
    }

    public Department getDepartment() {
            return department;
        }

    public void setDepartment(Department department) {
                this.department = department;
    }
}
