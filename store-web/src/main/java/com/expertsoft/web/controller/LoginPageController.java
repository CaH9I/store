package com.expertsoft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

    public static final String LOGIN_URL = "/login";

    @GetMapping(LOGIN_URL)
    public String login() {
        return "login";
    }
}
