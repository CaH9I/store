package com.expertsoft.core.service;

import com.expertsoft.core.model.AccountRepository;
import com.expertsoft.core.model.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountService extends RepositoryService<Account, Long, AccountRepository> {

    @Autowired
    public AccountService(final AccountRepository accountRepository) {
        super(accountRepository, Account.class);
    }

    @Transactional(readOnly = true)
    public Optional<Account> findByEmail(final String email) {
        return findBySimpleNaturalId(email);
    }
}
