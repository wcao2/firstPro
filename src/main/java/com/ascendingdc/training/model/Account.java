package com.ascendingdc.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "account")
public class Account {
    public Account(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//1.01
    @Column(name="id",columnDefinition = "SERIAL")
    private Long id;
    @Column(name = "account_type")
    //@JsonView({DepartmentViews.Employee.class})
    private String account_type;
    @Column(name="balance")
   // @JsonView({DepartmentViews.Employee.class})
    private BigDecimal balance ;

    @Column(name="create_date")
    private LocalDate createDate;


    //have joincolumn is owing side
    //lazy means only get the data from account(select * from account)
    //if I want to get employee,in HQL, use join fetc
    //fetch type(eager): HQL: select a from Account a join fetch a.employee.id=:Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    @JsonIgnore //如果是lazy 要加上 因为lazy拿不到employee对象
    private Employee employee;

    public Account(String account_type, BigDecimal balance, LocalDate createDate, Employee employee) {
        this.account_type = account_type;
        this.balance = balance;
        this.createDate = createDate;
        this.employee = employee;
    }

    public Account(String account_type, BigDecimal balance, Employee employee) {
        this.account_type = account_type;
        this.balance = balance;
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

//    public Date getCreate_date() {
//        return create_date;
//    }
//
//    public void setCreate_date(Date create_date) {
//        this.create_date = create_date;
//    }

//    public Long getEmployee_id() {
//        return employee_id;
//    }
//
//    public void setEmployee_id(Long employe_id) {
//        this.employee_id = employe_id;
//    }
}
