package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.entity.Account;
import com.expertsoft.core.test.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.expertsoft.core.test.TestObjectFactory.getTestAccount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AccountServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void findByEmail() {
        Account testAccount = getTestAccount();

        Account account = accountService.findByEmail(testAccount.getEmail())
                .orElseThrow(RecordNotFoundException::new);

        assertEquals(testAccount, account);
    }

    @Test
    public void findByEmailNotExists() {
        Optional<Account> account = accountService.findByEmail("fake email");

        assertFalse(account.isPresent());
    }
}
