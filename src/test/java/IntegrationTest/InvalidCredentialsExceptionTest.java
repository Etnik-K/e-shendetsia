package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import edu.unipr.eshendetsia.exception.InvalidCredentialsException;
import org.junit.jupiter.api.Test;

class InvalidCredentialsExceptionTest {

    @Test
    void testExceptionMessage() {
        InvalidCredentialsException exception = new InvalidCredentialsException();
        assertEquals("Perdoruesi/Fjalekalimi eshte gabim", exception.getMessage());
    }

    @Test
    void testExceptionThrown() {
        assertThrows(InvalidCredentialsException.class, () -> {
            throw new InvalidCredentialsException();
        });
    }
}