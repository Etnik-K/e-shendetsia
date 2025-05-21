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

class NotificationServiceImplementationTest {

    @Mock
    private NotificationRepository notificationRepository;

    private NotificationServiceImplementation notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        notificationService = new NotificationServiceImplementation(notificationRepository);
    }

    @Test
    void testSave() {
        Notification notification = new Notification();
        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification savedNotification = notificationService.save(notification);

        assertNotNull(savedNotification);
        verify(notificationRepository).save(notification);
    }

    @Test
    void testGetByUser() {
        Long userId = 1L;
        List<Notification> notifications = Arrays.asList(new Notification(), new Notification());
        when(notificationRepository.findByUserId(userId)).thenReturn(notifications);

        List<Notification> result = notificationService.getByUser(userId);

        assertEquals(notifications, result);
        verify(notificationRepository).findByUserId(userId);
    }

    @Test
    void testDelete() {
        Long notificationId = 1L;

        notificationService.delete(notificationId);

        verify(notificationRepository).deleteById(notificationId);
    }
}