package com.expertsoft.web.test;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;

import static com.expertsoft.web.security.SecurityConstants.ROLE_ADMIN;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@WithMockUser(roles = ROLE_ADMIN)
public @interface WithAdmin {}
