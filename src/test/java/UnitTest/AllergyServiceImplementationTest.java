package UnitTest;

import edu.unipr.eshendetsia.model.Allergy;
import edu.unipr.eshendetsia.repository.AllergyRepository;
import edu.unipr.eshendetsia.service.implementation.AllergyServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AllergyServiceImplementationTest {

    @Mock
    private AllergyRepository allergyRepository;

    private AllergyServiceImplementation allergyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        allergyService = new AllergyServiceImplementation(allergyRepository);
    }

    @Test
    void testSave() {
        Allergy allergy = new Allergy();
        when(allergyRepository.save(any(Allergy.class))).thenReturn(allergy);

        Allergy savedAllergy = allergyService.save(allergy);

        assertNotNull(savedAllergy);
        verify(allergyRepository).save(allergy);
    }

    @Test
    void testGetByUserId() {
        Long userId = 1L;
        List<Allergy> expectedAllergies = Arrays.asList(new Allergy(), new Allergy());
        when(allergyRepository.findByUserId(userId)).thenReturn(expectedAllergies);

        List<Allergy> actualAllergies = allergyService.getByUserId(userId);

        assertEquals(expectedAllergies, actualAllergies);
        verify(allergyRepository).findByUserId(userId);
    }

    @Test
    void testDelete() {
        Long allergyId = 1L;
        doNothing().when(allergyRepository).deleteById(allergyId);

        allergyService.delete(allergyId);

        verify(allergyRepository).deleteById(allergyId);
    }
}