package UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.unipr.eshendetsia.exception.UnauthorizedException;
import edu.unipr.eshendetsia.model.History;
import edu.unipr.eshendetsia.repository.HistoryRepository;
import edu.unipr.eshendetsia.service.implementation.HistoryServiceImplementation;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class HistoryServiceImplementationTest {

    @Mock
    private HistoryRepository historyRepository;

    private HistoryServiceImplementation historyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        historyService = new HistoryServiceImplementation(historyRepository);
    }

    @Test
    void save_ValidHistory_ReturnsHistory() throws UnauthorizedException, JWTVerificationException {
        // Arrange
        History history = new History();
        when(historyRepository.save(any(History.class))).thenReturn(history);

        // Act
        History result = historyService.save(history);

        // Assert
        assertNotNull(result);
        verify(historyRepository).save(history);
    }

    @Test
    void save_UnauthorizedException_ThrowsException() {
        // Arrange
        History history = new History();
        when(historyRepository.save(any(History.class))).thenThrow(UnauthorizedException.class);

        // Act & Assert
        assertThrows(UnauthorizedException.class, () -> historyService.save(history));
    }

    @Test
    void save_JWTVerificationException_ThrowsException() {
        // Arrange
        History history = new History();
        when(historyRepository.save(any(History.class))).thenThrow(JWTVerificationException.class);

        // Act & Assert
        assertThrows(JWTVerificationException.class, () -> historyService.save(history));
    }
}