package com.ascendingdc.training.model;

import com.ascendingdc.training.model.views.JsView;
import com.ascendingdc.training.model.views.JsView1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.CreationTimestamp;

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
    @JsonView({JsView.Anonymous.class})
    private Long id;

    @Column(name = "name")
    @JsonView({JsView.User.class, JsView1.User1.class})
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    @JsonView({JsView.User.class})
    private String first_name;

    @Column(name = "last_name")
    @JsonView({JsView.User.class})
    private String last_name ;

    @Column(name = "email")
    @JsonView({JsView.User.class,JsView1.User1.class})
    private String email;

    @Column(name = "address")
    @JsonView({JsView.User.class,JsView1.User1.class})
    private String address;

    @Column(name = "hired_date")
    @JsonView({JsView.User.class})
    @CreationTimestamp
    private LocalDate hired_date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonView({JsView.User.class})
    private Department department;

    //mappedBy object in account.java which have JoinColumn
    //CascadeType in here means: when I delete employee, it will be deleted all the records on accounts
    // @JsonIgnore
    @OneToMany(mappedBy = "employee",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JsonView({JsView.Admin.class})
    private Set<Account> account;

    @ManyToMany(cascade=CascadeType.ALL)//fetch=FetchType.EAGER
    @JsonView({JsView.Admin.class})
    @JoinTable(name="employees_roles",joinColumns = {@JoinColumn(name="employee_id")},inverseJoinColumns = {@JoinColumn(name="role_id")})
    private List<Role> roles;
    public List<Role> getRoles() {//due to lazy, so getRole needs join fetch
        return roles;
    }

    @OneToMany(mappedBy = "employee")
    private List<Image> image;

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

    public String getPassword() {return password; }

    //in my project, I use 封装 to encrypt my password and use set method to set my password
    //封装： we 封装 logic in Java class, other people can invoke without knowing the logic
    public void setPassword(String password) {this.password = DigestUtils.md5Hex(password.trim()); }
}
