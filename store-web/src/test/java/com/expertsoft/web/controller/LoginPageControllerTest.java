package com.expertsoft.web.controller;

import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import static com.expertsoft.web.controller.LoginPageController.LOGIN_URL;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class LoginPageControllerTest extends WebApplicationTest {

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithAnonymousUser
    public void login() throws Exception {
        mockMvc.perform(get(LOGIN_URL))
                .andExpect(view().name("login"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void loginAuthorizedUser() throws Exception {
        mockMvc.perform(get(LOGIN_URL))
                .andExpect(status().isForbidden());
    }
}
