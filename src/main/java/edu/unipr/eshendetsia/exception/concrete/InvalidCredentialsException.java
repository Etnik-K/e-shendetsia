package edu.unipr.eshendetsia.exception.concrete;

import edu.unipr.eshendetsia.exception.HttpException;
import org.springframework.http.HttpStatus;

/**
 * Perjashtim qe hidhet kur perdoruesi vendos kredenciale te pasakta
 * gjate procesit te verifikimit.
 * Ky perjashtim zgjeron HttpException dhe perdoret kryesisht
 * kur perdoruesi vendos gabim emrin ose fjalekalimin.
 */
public class InvalidCredentialsException extends HttpException {
    /**
     * Konstruktori default i cili vendos mesazhin e gabimit
     */
    public InvalidCredentialsException() {
        super("Perdoruesi/Fjalekalimi eshte gabim", HttpStatus.UNAUTHORIZED);
    }
}
