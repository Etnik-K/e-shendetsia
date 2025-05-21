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

/**
 * Klasa e testit per implementimin e sherbimit te historikut
 * Teston ruajtjen e historikut dhe menaxhimin e gabimeve
 */
class HistoryServiceImplementationTest {

    @Mock
    private HistoryRepository historyRepository;

    private HistoryServiceImplementation historyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        historyService = new HistoryServiceImplementation(historyRepository);
    }

    /**
     * Teston ruajtjen e suksesshme te historikut
     * Duke verifikuar qe historiku ruhet dhe kthehet sic duhet
     */
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

    /**
     * Teston rastin kur perdoruesi nuk eshte i autorizuar
     * Duhet te hedhe gabimin UnauthorizedException
     */
    @Test
    void save_UnauthorizedException_ThrowsException() {
        // Arrange
        History history = new History();
        when(historyRepository.save(any(History.class))).thenThrow(UnauthorizedException.class);

        // Act & Assert
        assertThrows(UnauthorizedException.class, () -> historyService.save(history));
    }

    /**
     * Teston rastin kur verifikimi i JWT deshton
     * Duhet te hedhe gabimin JWTVerificationException
     */
    @Test
    void save_JWTVerificationException_ThrowsException() {
        // Arrange
        History history = new History();
        when(historyRepository.save(any(History.class))).thenThrow(JWTVerificationException.class);

        // Act & Assert
        assertThrows(JWTVerificationException.class, () -> historyService.save(history));
    }
}