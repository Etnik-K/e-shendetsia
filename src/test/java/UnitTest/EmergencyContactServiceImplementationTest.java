package UnitTest;

import edu.unipr.eshendetsia.model.EmergencyContact;
import edu.unipr.eshendetsia.repository.EmergencyContactRepository;
import edu.unipr.eshendetsia.service.implementation.EmergencyContactServiceImplementation;
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
 * Klasa per testimin e EmergencyContactServiceImplementation.
 * Teston metodat e ruajtjes, marrjes dhe fshirjes se kontakteve emergjente.
 * Perdor Mockito per te simuluar sjelljen e repository.
 */
@ExtendWith(MockitoExtension.class)
class EmergencyContactServiceImplementationTest {

    @Mock
    private EmergencyContactRepository repository;

    private EmergencyContactServiceImplementation service;

    /**
     * Inicializon mjedisin e testimit para cdo testi.
     * Krijon instance te re te service me repository te mockuar.
     */
    @BeforeEach
    void setUp() {
        service = new EmergencyContactServiceImplementation(repository);
    }

    /**
     * Teston metodat e ruajtjes se kontaktit emergjent.
     * Verteton qe kontakti ruhet me sukses dhe repository thirret sakte.
     */
    @Test
    void testSave() {
        EmergencyContact contact = new EmergencyContact();
        when(repository.save(contact)).thenReturn(contact);

        EmergencyContact result = service.save(contact);

        assertNotNull(result);
        verify(repository).save(contact);
    }

    /**
     * Teston marrjen e kontakteve emergjente sipas ID te perdoruesit.
     * Kontrollon nese lista e kthyer perputhet me te dhenat e pritura.
     */
    @Test
    void testGetByUserId() {
        Long userId = 1L;
        List<EmergencyContact> expectedContacts = Arrays.asList(new EmergencyContact(), new EmergencyContact());
        when(repository.findByUserId(userId)).thenReturn(expectedContacts);

        List<EmergencyContact> result = service.getByUserId(userId);

        assertNotNull(result);
        assertEquals(expectedContacts.size(), result.size());
        verify(repository).findByUserId(userId);
    }

    /**
     * Teston fshirjen e kontaktit emergjent.
     * Verteton qe metoda e fshirjes ne repository thirret me ID e sakte.
     */
    @Test
    void testDelete() {
        Long id = 1L;
        doNothing().when(repository).deleteById(id);

        service.delete(id);

        verify(repository).deleteById(id);
    }
}