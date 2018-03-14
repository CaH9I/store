package com.expertsoft.web.test;

import com.expertsoft.web.WebApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = WebApplication.class)
@ActiveProfiles("test")
@Transactional
public abstract class WebApplicationTest {

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;
}
