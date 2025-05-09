package app.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Nuk jeni i autorizuar!");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
