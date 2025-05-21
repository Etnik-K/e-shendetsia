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

@ExtendWith(MockitoExtension.class)
class PerscriptionServiceImplementationTest {

    @Mock
    private PerscriptionRepository perscriptionRepository;

    private PerscriptionServiceImplementation perscriptionService;

    @BeforeEach
    void setUp() {
        perscriptionService = new PerscriptionServiceImplementation(perscriptionRepository);
    }

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