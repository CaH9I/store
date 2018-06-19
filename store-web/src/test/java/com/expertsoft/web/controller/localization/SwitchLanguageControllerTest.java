package com.expertsoft.web.controller.localization;

import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;

import static com.expertsoft.web.controller.localization.SwitchLanguageController.SWITCH_LANG_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class SwitchLanguageControllerTest extends WebApplicationTest {

    private static final String ROOT = "/";

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void setLanguage() throws Exception {
        mockMvc.perform(put(SWITCH_LANG_URL)
                .param("redirectUrl", ROOT))
                .andExpect(redirectedUrl(ROOT))
                .andExpect(status().is3xxRedirection());
    }
}
