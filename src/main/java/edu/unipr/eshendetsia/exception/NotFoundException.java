package edu.unipr.eshendetsia.exception;

/**
 * Perben nje perjashtim qe hidhet kur nje burim nuk gjendet
 * Ky perjashtim zgjeron RuntimeException dhe perdoret per te treguar
 * rastet kur nje kerkese per nje burim specifik deshtoi sepse
 * burimi nuk ekziston
 */
public class NotFoundException extends RuntimeException {
    /**
     * Konstruktori i perjashtimit
     *
     * @param message Mesazhi pershkrues i arsyes se perjashtimit
     */
    public NotFoundException(String message) {
        super(message);
    }
}
