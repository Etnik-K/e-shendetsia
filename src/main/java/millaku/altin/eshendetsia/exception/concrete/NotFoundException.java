package millaku.altin.eshendetsia.exception.concrete;

import millaku.altin.eshendetsia.exception.HttpException;
import org.springframework.http.HttpStatus;

/**
 * Perben nje perjashtim qe hidhet kur nje burim nuk gjendet
 * Ky perjashtim zgjeron HttpException dhe perdoret per te treguar
 * rastet kur nje kerkese per nje burim specifik deshtoi sepse
 * burimi nuk ekziston
 */
public class NotFoundException extends HttpException {
    /**
     * Konstruktori i perjashtimit
     *
     * @param message Mesazhi pershkrues i arsyes se perjashtimit
     */
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
