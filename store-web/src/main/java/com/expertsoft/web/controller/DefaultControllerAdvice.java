package com.expertsoft.web.controller;

import com.expertsoft.core.exception.RecordNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    public String notFound(HttpServletResponse response) {
        response.setStatus(SC_NOT_FOUND);
        return "error/404";
    }
}
