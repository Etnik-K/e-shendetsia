package edu.unipr.eshendetsia.exception.concrete;

import edu.unipr.eshendetsia.exception.HttpException;
import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends HttpException {
    public InternalServerErrorException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
