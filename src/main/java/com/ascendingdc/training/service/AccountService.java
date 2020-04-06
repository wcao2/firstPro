package com.ascendingdc.training.service;

import com.ascendingdc.training.model.Account;
import com.ascendingdc.training.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    public Account getAccountById(Long id){
        return accountDao.getAccountById(id);
    }

    public Account save(Account account){
        return accountDao.save(account);
    }

    public boolean delete(Long id){
        return accountDao.delete(id);
    }
}





























