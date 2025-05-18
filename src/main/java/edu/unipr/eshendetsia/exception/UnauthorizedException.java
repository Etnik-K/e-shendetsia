package edu.unipr.eshendetsia.exception;

/**
 * Perjashtime e cila hidhet kur perdoruesi nuk ka autorizimin e duhur
 * per te kryer nje veprim te caktuar ne sistem.
 * Kjo klase zgjeron RuntimeException dhe perdoret per te menaxhuar
 * rastet kur aksesi mohohet per shkak te mungeses se privilegjeve.
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
