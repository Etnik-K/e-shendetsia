package UnitTest;

import edu.unipr.eshendetsia.model.Doctor;
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

class DoctorServiceImplementationTest {

    @Mock
    private DoctorRepository doctorRepository;

    private DoctorServiceImplementation doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorService = new DoctorServiceImplementation(doctorRepository);
    }

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