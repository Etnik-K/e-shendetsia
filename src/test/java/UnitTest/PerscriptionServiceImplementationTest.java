package UnitTest;

import edu.unipr.eshendetsia.model.entity.Perscription;
import edu.unipr.eshendetsia.repository.PerscriptionRepository;
import edu.unipr.eshendetsia.service.implementation.PerscriptionServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Klasa e testimit per implementimin e sherbimit te perscriptioneve.
 * Perdor Mockito per te testuar funksionalitetin e PerscriptionServiceImplementation.
 */
@ExtendWith(MockitoExtension.class)
class PerscriptionServiceImplementationTest {

    @Mock
    private PerscriptionRepository perscriptionRepository;

    private PerscriptionServiceImplementation perscriptionService;

    @BeforeEach
    void setUp() {
        perscriptionService = new PerscriptionServiceImplementation(perscriptionRepository);
    }

    /**
     * Teston metoden findByUserId.
     * Kontrollon nese metoda kthen listen e perscriptioneve per nje ID te caktuar te perdoruesit.
     */
    @Test
    void findByUserId_ShouldReturnPerscriptionList() {
        // Arrange
        Long userId = 1L;
        List<Perscription> expectedPerscriptions = Arrays.asList(new Perscription(), new Perscription());
        when(perscriptionRepository.findByUserId(userId)).thenReturn(expectedPerscriptions);

        // Act
        List<Perscription> actualPerscriptions = perscriptionService.findByUserId(userId);

        // Assert
        assertEquals(expectedPerscriptions, actualPerscriptions);
        verify(perscriptionRepository).findByUserId(userId);
    }

    /**
     * Teston metoden findByDoctorId.
     * Kontrollon nese metoda kthen listen e perscriptioneve per nje ID te caktuar te doktorit.
     */
    @Test
    void findByDoctorId_ShouldReturnPerscriptionList() {
        // Arrange
        Long doctorId = 1L;
        List<Perscription> expectedPerscriptions = Arrays.asList(new Perscription(), new Perscription());
        when(perscriptionRepository.findByDoctorId(doctorId)).thenReturn(expectedPerscriptions);

        // Act
        List<Perscription> actualPerscriptions = perscriptionService.findByDoctorId(doctorId);

        // Assert
        assertEquals(expectedPerscriptions, actualPerscriptions);
        verify(perscriptionRepository).findByDoctorId(doctorId);
    }

    /**
     * Teston metoden save.
     * Verifikon nese metoda ruan dhe kthen perscriptionin e ruajtur me sukses.
     */
    @Test
    void save_ShouldReturnSavedPerscription() {
        // Arrange
        Perscription perscriptionToSave = new Perscription();
        when(perscriptionRepository.save(perscriptionToSave)).thenReturn(perscriptionToSave);

        // Act
        Perscription savedPerscription = perscriptionService.save(perscriptionToSave);

        // Assert
        assertEquals(perscriptionToSave, savedPerscription);
        verify(perscriptionRepository).save(perscriptionToSave);
    }
}