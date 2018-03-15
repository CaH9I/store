package com.expertsoft.web.test;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@WithMockUser(roles = "ADMIN")
public @interface WithAdmin {}
