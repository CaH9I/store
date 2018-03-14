package com.expertsoft.web.controller;

import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.util.List;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhones;
import static java.util.Arrays.asList;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(Parameterized.class)
public class ProductListControllerTest extends WebApplicationTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Parameters
    public static List<String> data() {
        return asList("/", "/product-list");
    }

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Parameter
    public String url;

    @Test
    public void productList() throws Exception {
        mockMvc.perform(get(url))
                .andExpect(model().attribute("mobilePhones", getTestMobilePhones()))
                .andExpect(model().attributeExists("cartView"))
                .andExpect(view().name("productList"))
                .andExpect(status().is(SC_OK));
    }
}
