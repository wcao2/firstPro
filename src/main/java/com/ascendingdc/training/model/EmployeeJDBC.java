package com.ascendingdc.training.model;

public class EmployeeJDBC {
    private long id;
    private String name;
    private String first_name;
    private String last_name ;
    private String email;
    private String address;
    private String hired_date;
    private Long department_id;

    public EmployeeJDBC(){}
    public EmployeeJDBC(String name, String first_name, String last_name,String email,String address,String hired_date,Long department_id){
        this.name=name;
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.address=address;
        this.hired_date=hired_date;
        this.department_id=department_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getHired_date() {
        return hired_date;
    }

    public void setHired_date(String hired_date) {
        this.hired_date = hired_date;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }
}
