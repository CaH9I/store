package com.expertsoft.web.controller.localization;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SwitchLanguageController {

    public static final String SWITCH_LANG_URL = "/language";

    @PutMapping(SWITCH_LANG_URL)
    public String setLanguage(@RequestParam String redirectUrl) {
        return "redirect:" + redirectUrl;
    }
}
