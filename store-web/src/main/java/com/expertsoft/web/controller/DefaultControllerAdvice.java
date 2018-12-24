package com.expertsoft.web.controller;

import com.expertsoft.core.commerce.ShoppingCartView;
import com.expertsoft.core.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@ControllerAdvice
public class DefaultControllerAdvice {

    private static final List<String> SUPPORTED_LANGUAGES = List.of("en", "ru");

    private final ShoppingCartView cartView;

    @Autowired
    public DefaultControllerAdvice(final ShoppingCartView cartView) {
        this.cartView = cartView;
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public String notFound(HttpServletResponse response) {
        response.setStatus(SC_NOT_FOUND);
        return "error/404";
    }

    @ModelAttribute("cartView")
    public ShoppingCartView cartView() {
        return cartView;
    }

    @ModelAttribute("currentLanguage")
    public String currentLanguage() {
        return getLocale().getLanguage();
    }

    @ModelAttribute("languages")
    public List<String> supportedLanguages() {
        return SUPPORTED_LANGUAGES;
    }
}
