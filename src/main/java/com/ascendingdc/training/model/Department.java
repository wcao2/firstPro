package com.ascendingdc.training.model;

import javax.persistence.*;
import java.util.List;

//domain,model,entity
@Entity
@Table(name="department")
public class Department {
    public Department(){}

    public Department(String name, String description,String location){
        this.name=name;
        this.description=description;
        this.location=location;
    }
    @OneToMany(mappedBy = "department",cascade = CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<Employee> employee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//1.01
    @Column(name="id",columnDefinition = "SERIAL")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="location")
    private String location;

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }
}
