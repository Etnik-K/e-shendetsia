package UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.unipr.eshendetsia.model.Notification;
import edu.unipr.eshendetsia.repository.NotificationRepository;
import edu.unipr.eshendetsia.service.implementation.NotificationServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Testi per implementimin e sherbimit te njoftimeve
 * Kontrollon funksionalitetin e ruajtjes, marrjes dhe fshirjes se njoftimeve
 */
class NotificationServiceImplementationTest {

    @Mock
    private NotificationRepository notificationRepository;

    private NotificationServiceImplementation notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationService = new NotificationServiceImplementation(notificationRepository);
    }

    /**
     * Teston metoden e ruajtjes se njoftimit
     * Kontrollon nese njoftimi ruhet me sukses ne repository
     */
    @Test
    void testSave() {
        Notification notification = new Notification();
        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification savedNotification = notificationService.save(notification);

        assertNotNull(savedNotification);
        verify(notificationRepository).save(notification);
    }

    /**
     * Teston metoden e marrjes se njoftimeve nga perdoruesi
     * Kontrollon nese merren te gjitha njoftimet e nje perdoruesi
     */
    @Test
    void testGetByUser() {
        Long userId = 1L;
        List<Notification> notifications = Arrays.asList(new Notification(), new Notification());
        when(notificationRepository.findByUserId(userId)).thenReturn(notifications);

        List<Notification> result = notificationService.getByUser(userId);

        assertEquals(notifications, result);
        verify(notificationRepository).findByUserId(userId);
    }

    /**
     * Teston metoden e fshirjes se njoftimit
     * Kontrollon nese njoftimi fshihet me sukses nga repository
     */
    @Test
    void testDelete() {
        Long notificationId = 1L;

        notificationService.delete(notificationId);

        verify(notificationRepository).deleteById(notificationId);
    }
}