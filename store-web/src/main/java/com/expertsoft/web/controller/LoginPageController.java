package com.expertsoft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.expertsoft.web.controller.LoginPageController.LOGIN_URL;

@Controller
@RequestMapping(LOGIN_URL)
public class LoginPageController {

    public static final String LOGIN_URL = "/login";

    @GetMapping
    public String login() {
        return "login";
    }
}
