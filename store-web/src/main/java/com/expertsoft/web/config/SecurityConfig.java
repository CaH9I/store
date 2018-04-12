package com.expertsoft.web.config;

import com.expertsoft.web.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.expertsoft.web.controller.LoginPageController.LOGIN_URL;
import static com.expertsoft.web.security.SecurityConstants.ADMIN;
import static com.expertsoft.web.security.SecurityConstants.ROLE_ADMIN;
import static com.expertsoft.web.security.SecurityConstants.ROLE_USER;
import static java.lang.String.format;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${remember.me.key}")
    private String rememberMeKey;

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(final UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admin/**").hasRole(ADMIN)
                .antMatchers(LOGIN_URL).anonymous()
            .and()
                .formLogin()
                .loginPage(LOGIN_URL)
            .and()
                .logout().logoutSuccessUrl("/")
            .and()
                .rememberMe()
                .key(rememberMeKey);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public RoleHierarchyVoter roleHierarchyVoter() {
        return new RoleHierarchyVoter(roleHierarchy());
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(format("%s > %s", ROLE_ADMIN, ROLE_USER));
        return roleHierarchy;
    }
}
