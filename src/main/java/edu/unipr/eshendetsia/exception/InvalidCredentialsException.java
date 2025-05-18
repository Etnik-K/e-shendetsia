package edu.unipr.eshendetsia.exception;

/**
 * Perjashtim qe hidhet kur perdoruesi vendos kredenciale te pasakta
 * gjate procesit te verifikimit.
 * <p>
 * Ky perjashtim zgjeron RuntimeException dhe perdoret kryesisht
 * kur perdoruesi vendos gabim emrin ose fjalekalimin.
 */
public class InvalidCredentialsException extends RuntimeException {
    /**
     * Konstruktori default i cili vendos mesazhin e gabimit
     */
    public InvalidCredentialsException() {
        super("Perdoruesi/Fjalekalimi eshte gabim");
    }
}
