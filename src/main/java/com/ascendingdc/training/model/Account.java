package com.ascendingdc.training.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account {
    public Account(){}
    public Account(String name, String account_type, String balance, String createDate,long employe_id){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//1.01
    @Column(name="id",columnDefinition = "SERIAL")
    private Long id;
    @Column(name = "account_type")
    private String account_type;
    @Column(name="balance")
    private BigDecimal balance ;

    @Column(name="create_date")
    private LocalDate createDate;
//    @Column(name="employee_id")
//    private Long employee_id;

    //have joincolumn is owing side
    //lazy means only get the data from account(select * from account)
    //if I want to get employee,in HQL, use join fetc
    //fetch type(eager): HQL: select a from Account a join fetch a.employee.id=:Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

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
