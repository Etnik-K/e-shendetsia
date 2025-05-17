package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Notification;
import edu.unipr.eshendetsia.repository.NotificationRepository;
import edu.unipr.eshendetsia.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImplementation implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImplementation(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification save(Notification n) {
        return notificationRepository.save(n);
    }

    public List<Notification> getByUser(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public void markAsRead(Long id) {
        Notification n = notificationRepository.findById(id).orElse(null);
        if (n != null) {
            n.setRead(true);
            notificationRepository.save(n);
        }
    }

    public void delete(Long id) {
        notificationRepository.deleteById(id);

    }
}