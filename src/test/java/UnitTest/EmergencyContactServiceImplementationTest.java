package UnitTest;

import edu.unipr.eshendetsia.model.entity.EmergencyContact;
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

@ExtendWith(MockitoExtension.class)
class EmergencyContactServiceImplementationTest {

    @Mock
    private EmergencyContactRepository repository;

    private EmergencyContactServiceImplementation service;

    @BeforeEach
    void setUp() {
        service = new EmergencyContactServiceImplementation(repository);
    }

    @Test
    void testSave() {
        EmergencyContact contact = new EmergencyContact();
        when(repository.save(contact)).thenReturn(contact);

        EmergencyContact result = service.save(contact);

        assertNotNull(result);
        verify(repository).save(contact);
    }

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

    @Test
    void testDelete() {
        Long id = 1L;
        doNothing().when(repository).deleteById(id);

        service.delete(id);

        verify(repository).deleteById(id);
    }
}