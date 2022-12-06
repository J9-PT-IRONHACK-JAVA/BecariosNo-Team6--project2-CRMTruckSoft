
package com.ironhack.team6crm.service;

import com.ironhack.team6crm.model.Account;
import com.ironhack.team6crm.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(long selectedId) {
        return accountRepository.findById(selectedId);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }
}