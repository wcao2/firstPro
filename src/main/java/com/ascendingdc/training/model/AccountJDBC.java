package com.ascendingdc.training.model;

public class AccountJDBC {
    private Long id;
    private String account_type;
    private String balance ;
    private String createDate;
    private Long employe_id;

    public AccountJDBC(){}
    public AccountJDBC( String account_type, String balance,String createDate,Long employe_id){
        this.account_type=account_type;
        this.balance=balance;
        this.createDate=createDate;
        this.employe_id=employe_id;

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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(Long employe_id) {
        this.employe_id = employe_id;
    }
}
