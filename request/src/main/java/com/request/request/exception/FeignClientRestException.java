package com.request.request.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FeignClientRestException extends RuntimeException {

    public FeignClientRestException(String message) {
        super(message);
    }
}
