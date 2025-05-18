package edu.unipr.eshendetsia.exception;

/**
 * Perjashtime qe hidhet kur nje perdorues nuk ka role te caktuara
 * ne sistem. Kjo klase zgjeron RuntimeException dhe perdoret per
 * te trajtuar rastet kur mungojne rolet e perdoruesit.
 */
public class NoRolesException extends RuntimeException {
    /**
     * Konstruktori i klases NoRolesException
     *
     * @param message Mesazhi pershkrues i problemit
     */
    public NoRolesException(String message) {
        super(message);
    }
}
