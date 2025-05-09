package app.exception;

public class InvalidCredentialsException extends RuntimeException {
    public static final String MESSAGE = "Perdoruesi/Fjalekalimi eshte gabim";
    public InvalidCredentialsException() {
        super(MESSAGE);
    }
}
