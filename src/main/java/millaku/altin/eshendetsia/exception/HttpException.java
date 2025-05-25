package millaku.altin.eshendetsia.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Me gjase eshte berllog
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class HttpException extends RuntimeException {

    private final HttpStatus httpStatus;

    public HttpException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}