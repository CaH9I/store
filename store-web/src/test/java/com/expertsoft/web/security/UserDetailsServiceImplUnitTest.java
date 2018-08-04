package com.expertsoft.web.security;

import com.expertsoft.core.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static com.expertsoft.core.test.TestObjectFactory.getTestAccount;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplUnitTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private AccountService accountService;

    @Test
    public void loadUserByUsername() {
        // given
        var testAccount = getTestAccount();
        when(accountService.findByUsername(testAccount.getUsername())).thenReturn(Optional.of(testAccount));

        // when
        var user = userDetailsService.loadUserByUsername(testAccount.getUsername());

        // then
        assertEquals(testAccount.getUsername(), user.getUsername());
        assertEquals(testAccount.getPassword(), user.getPassword());

        var expectedRoles = testAccount.getRoles()
                .stream()
                .map(role -> "ROLE_" + role.getName())
                .collect(toSet());

        var actualRoles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(toSet());

        assertEquals(expectedRoles, actualRoles);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameNotExists() {
        var unknownUser = "user not exists";
        when(accountService.findByUsername(unknownUser)).thenReturn(Optional.empty());

        userDetailsService.loadUserByUsername(unknownUser);
    }
}
