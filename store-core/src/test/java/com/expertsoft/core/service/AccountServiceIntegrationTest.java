package com.expertsoft.core.service;

import com.expertsoft.core.test.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.expertsoft.core.test.TestObjectFactory.getTestAccount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void findByUsername() {
        var testAccount = getTestAccount();

        var accountOpt = accountService.findByUsername(testAccount.getUsername());

        assertTrue(accountOpt.isPresent());
        assertEquals(testAccount, accountOpt.get());
    }

    @Test
    public void findByUsernameNotExists() {
        var accountOpt = accountService.findByUsername("fake email");

        assertFalse(accountOpt.isPresent());
    }
}
