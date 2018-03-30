package com.expertsoft.web.service;

import com.expertsoft.core.model.AccountRepository;
import com.expertsoft.core.model.entity.Account;
import com.expertsoft.core.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public UserDetailsServiceImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        Account account = accountRepository.findByEmailWithRoles(username)
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
