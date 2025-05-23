package UnitTest;

import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.repository.DoctorRepository;
import edu.unipr.eshendetsia.service.implementation.DoctorServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Ky test kontrollon funksionalitetin e klases se implementimit te sherbimit te doktorit.
 * Perfshin testimet per metoda te tilla si marrja e te gjithe doktoreve dhe marrja e
 * doktorit me ane te ID-se. Perdor Mockito per te simuluar repository-n dhe JUnit
 * per te ekzekutuar testimet.
 */
class DoctorServiceImplementationTest {

    @Mock
    private DoctorRepository doctorRepository;

    private DoctorServiceImplementation doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorService = new DoctorServiceImplementation(doctorRepository);
    }

    /**
     * Teston metoden getAllDoctors qe kthen nje liste me doktore.
     * Kontrollon nese lista e kthyer nga sherbimi perputhet me listen e pritur.
     */
    @Test
    void getAllDoctors_ShouldReturnListOfDoctors() {
        // Arrange
        Doctor doctor1 = new Doctor();
        Doctor doctor2 = new Doctor();
        List<Doctor> expectedDoctors = Arrays.asList(doctor1, doctor2);
        when(doctorRepository.findAll()).thenReturn(expectedDoctors);

        // Act
        List<Doctor> actualDoctors = doctorService.getAllDoctors();

        // Assert
        assertEquals(expectedDoctors, actualDoctors);
    }

    /**
     * Teston metoden getDoctorById kur doktori ekziston.
     * Kontrollon nese doktori i kthyer nga sherbimi perputhet me doktorin e pritur.
     */
    @Test
    void getDoctorById_WhenDoctorExists_ShouldReturnDoctor() {
        // Arrange
        Doctor expectedDoctor = new Doctor();
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(expectedDoctor));

        // Act
        Optional<Doctor> actualDoctor = doctorService.getDoctorById(1L);

        // Assert
        assertTrue(actualDoctor.isPresent());
        assertEquals(expectedDoctor, actualDoctor.get());
    }

    /**
     * Teston metoden getDoctorById kur doktori nuk ekziston.
     * Kontrollon nese metoda kthen nje Optional bosh.
     */
    @Test
    void getDoctorById_WhenDoctorDoesNotExist_ShouldReturnEmpty() {
        // Arrange
        when(doctorRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Doctor> actualDoctor = doctorService.getDoctorById(1L);

        // Assert
        assertTrue(actualDoctor.isEmpty());
    }
}