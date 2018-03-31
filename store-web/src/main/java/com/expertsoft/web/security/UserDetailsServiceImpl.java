package com.expertsoft.web.security;

import com.expertsoft.core.model.entity.Account;
import com.expertsoft.core.model.entity.Role;
import com.expertsoft.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    @Autowired
    public UserDetailsServiceImpl(final AccountService accountService) {
        this.accountService = accountService;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) {
        Account account = accountService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found: " + username));

        return User.withUsername(username)
                .password(account.getPassword())
                .roles(account.getRoles()
                        .stream()
                        .map(Role::getName)
                        .toArray(String[]::new))
                .build();
    }
}
