package UnitTest;

import edu.unipr.eshendetsia.model.entity.Appointment;
import edu.unipr.eshendetsia.repository.AppointmentRepository;
import edu.unipr.eshendetsia.service.implementation.AppointmentServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * Klasa e testimit per sherbimet e termineve
 * Teston implementimin e sherbimeve te termineve duke perdorur Mockito
 */
@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplementationTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceImplementation appointmentService;

    private Appointment appointment;


    @BeforeEach
    void setUp() {
        appointment = new Appointment();
        appointment.setId(1L);
        appointment.setUserId(1L);
        appointment.setDoctorId(2L);
        appointment.setAppointmentTime(LocalDateTime.now());
        appointment.setReason("Checkup");
        appointment.setStatus("SCHEDULED");
    }


    /**
     * Teston ruajtjen e terminit
     * Kontrollon nese termini ruhet me sukses dhe ka te gjitha te dhenat e sakta
     */
    @Test
    void testSaveAppointment() {
        // Arrange
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        Appointment savedAppointment = appointmentService.save(appointment);

        // Assert
        assertNotNull(savedAppointment);
        assertEquals(1L, savedAppointment.getId());
        assertEquals("SCHEDULED", savedAppointment.getStatus());
        verify(appointmentRepository, times(1)).save(appointment);
    }


    /**
     * Teston anulimin e suksesshem te terminit
     * Kontrollon nese termini anulohet me sukses dhe statusi ndryshohet ne CANCELED
     */
    @Test
    void testCancelAppointment_Success() {
        // Arrange
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        appointmentService.cancel(1L);

        // Assert
        assertEquals("CANCELED", appointment.getStatus());
        verify(appointmentRepository, times(1)).findById(1L);
        verify(appointmentRepository, times(1)).save(appointment);
    }


    /**
     * Teston rastin kur termini nuk gjendet gjate anulimit
     * Kontrollon sjelljen kur perpiqemi te anulojme nje termin qe nuk ekziston
     */
    @Test
    void testCancelAppointment_NotFound() {
        // Arrange
        when(appointmentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        appointmentService.cancel(1L);

        // Assert
        verify(appointmentRepository, times(1)).findById(1L);
        verify(appointmentRepository, never()).save(any(Appointment.class));
    }
}