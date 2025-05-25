package millaku.altin.eshendetsia.exception.concrete;

import millaku.altin.eshendetsia.exception.HttpException;
import org.springframework.http.HttpStatus;

/**
 * Perjashtime e cila hidhet kur perdoruesi nuk ka autorizimin e duhur
 * per te kryer nje veprim te caktuar ne sistem.
 * Kjo klase zgjeron HttpException dhe perdoret per te menaxhuar
 * rastet kur aksesi mohohet per shkak te mungeses se privilegjeve.
 */
public class UnauthorizedException extends HttpException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
