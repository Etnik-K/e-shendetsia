/**
 * Test klase per AllergyServiceImplementation.
 * Teston sherbimet e alergjive duke perdorur Mockito per te simuluar repository.
 */
package UnitTest;

import edu.unipr.eshendetsia.model.entity.Allergy;
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

    /**
     * Inicializon mjedisin e testimit para cdo testi.
     * Krijon mock objects dhe instance te sherbimit.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        allergyService = new AllergyServiceImplementation(allergyRepository);
    }

    /**
     * Teston metoden e ruajtjes se alergjise.
     * Kontrollon nese ruajtja kryhet me sukses dhe repository thirret sakte.
     */
    @Test
    void testSave() {
        Allergy allergy = new Allergy();
        when(allergyRepository.save(any(Allergy.class))).thenReturn(allergy);

        Allergy savedAllergy = allergyService.save(allergy);

        assertNotNull(savedAllergy);
        verify(allergyRepository).save(allergy);
    }

    /**
     * Teston marrjen e alergjive sipas ID te perdoruesit.
     * Verifikon nese lista e alergjive merret sakte nga repository.
     */
    @Test
    void testGetByUserId() {
        Long userId = 1L;
        List<Allergy> expectedAllergies = Arrays.asList(new Allergy(), new Allergy());
        when(allergyRepository.findByUserId(userId)).thenReturn(expectedAllergies);

        List<Allergy> actualAllergies = allergyService.getByUserId(userId);

        assertEquals(expectedAllergies, actualAllergies);
        verify(allergyRepository).findByUserId(userId);
    }

    /**
     * Teston fshirjen e nje alergji.
     * Kontrollon nese thirrja e fshirjes ne repository kryhet sakte.
     */
    @Test
    void testDelete() {
        Long allergyId = 1L;
        doNothing().when(allergyRepository).deleteById(allergyId);

        allergyService.delete(allergyId);

        verify(allergyRepository).deleteById(allergyId);
    }
}