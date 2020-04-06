package com.ascendingdc.training.model;

import com.ascendingdc.training.model.views.DepartmentViews;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

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

    //only employee can take department, department can not take employee
//    @JsonIgnore
    @OneToMany(mappedBy = "department",cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
    //对于department对象来说,转换employee的时候不转换employee下的
    // department 但对employee对象来说 直接转换employee不会影响department 意思getemployees还是会显示department
    @JsonIgnoreProperties({"department","account","roles"})    //department下的employee所有信息都能显示 除了department account roles
    //@JsonView({DepartmentViews.Manager.class})
    private List<Employee> employee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//1.01
    @Column(name="id",columnDefinition = "SERIAL")
    //@JsonView({DepartmentViews.Manager.class,DepartmentViews.Employee.class})
    private Long id;

    @Column(name="name")
    //@JsonView({DepartmentViews.Manager.class,DepartmentViews.Employee.class})
    private String name;

    @Column(name="description")
    //@JsonView({DepartmentViews.Manager.class})
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
