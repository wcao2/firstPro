package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Account;
import com.ascendingdc.training.model.Employee;

public interface AccountDao {
    Account getAccountById(Long id);
    //Account save(Account account,String employeeName);
    Account save(Account account);
    boolean delete(Long id);
}
