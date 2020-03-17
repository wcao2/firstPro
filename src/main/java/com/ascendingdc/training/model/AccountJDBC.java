package com.ascendingdc.training.model;

public class AccountJDBC {
    private long id;
    private String name;
    private String account_type;
    private String balance ;
    private String create_date;
    private Long employe_id;

    public AccountJDBC(){}
    public AccountJDBC(String name, String account_type, String balance,String create_date,Long employe_id){
        this.name=name;
        this.account_type=account_type;
        this.balance=balance;
        this.create_date=create_date;
        this.employe_id=employe_id;

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

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public Long getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(Long employe_id) {
        this.employe_id = employe_id;
    }
}
