package com.ascendingdc.training.model;


public class DepartmentJDBC{
    private Long id;
    private String name;
    private String description;
    private String location;

    public DepartmentJDBC(){}
    public DepartmentJDBC(String name, String description, String location){
        this.name=name;
        this.description=description;
        this.location=location;
    }
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}

    public String getLocation(){return location;}
    public void setLocation(String location){this.location=location;}
}