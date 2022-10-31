package com.progressoft.service;

import com.progressoft.model.Account;
import com.progressoft.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    public AccountRepository accountRepo;

    public Account saveOrUpdate(Account account) {
        return accountRepo.save(account);
    }

    public Account findAccountById(Long accountId) {
        return accountRepo.getReferenceById(accountId);
    }

    public void deleteAccountById(Long chequeId) {
        accountRepo.deleteById(chequeId);
    }

    public Iterable<Account> findAll() {
        return accountRepo.findAll();
    }

    public void saveAll(Iterable<Account> objects) {
        accountRepo.saveAll(objects);
    }

    public boolean accountExistsById(Long accountId) {
        return accountRepo.existsById(accountId);
    }
}
