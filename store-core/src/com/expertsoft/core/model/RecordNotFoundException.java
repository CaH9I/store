package com.expertsoft.core.model;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = NOT_FOUND, reason = "The data you have requested is not found")
public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5762636997176181487L;

    public RecordNotFoundException() {}

    public RecordNotFoundException(String msg) {
        super(msg);
    }

    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }

    public RecordNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RecordNotFoundException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
