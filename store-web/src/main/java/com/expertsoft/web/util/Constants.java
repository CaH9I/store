package com.expertsoft.web.util;

import java.util.List;

public interface Constants {

    String ROLE_PREFIX = "ROLE_";
    String ADMIN = "ADMIN";
    String ROLE_ADMIN = ROLE_PREFIX + ADMIN;
    String USER = "USER";
    String ROLE_USER = ROLE_PREFIX + USER;

    List<String> SUPPORTED_LANGUAGES = List.of("en", "ru");
}
