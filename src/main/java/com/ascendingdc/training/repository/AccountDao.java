package com.ascendingdc.training.repository;

import com.ascendingdc.training.model.Account;

public interface AccountDao {
//    Employee save(Employee e);
//    boolean delete(Employee e);
    Account save(Account account);
    boolean delete(Account account);
}
