package edu.unipr.eshendetsia.exception;

import edu.unipr.eshendetsia.exception.concrete.InvalidCredentialsException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Kthen nje pergjigje gabimi me mesazhin dhe kodin e dhene
     *
     * @param errorMessage Mesazhi i gabimit
     * @param code         Kodi i statusit HTTP
     * @return Pergjigja e formatuar me gabimin
     */
    private ResponseEntity<String> error(String errorMessage, HttpStatus code) {
        return ResponseEntity.status(code).body(errorMessage);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException e) {
        return this.error(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({NotFoundException.class, InvalidCredentialsException.class})
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return this.error(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
