package com.expertsoft.web.controller;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String notFound(HttpServletResponse resp) {
        resp.setStatus(SC_NOT_FOUND);
        return "error/404";
    }

}
