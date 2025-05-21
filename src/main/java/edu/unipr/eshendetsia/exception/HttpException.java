package edu.unipr.eshendetsia.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends Exception {
    private final HttpStatus httpStatus;
    public HttpException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
