package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.Notification;
import edu.unipr.eshendetsia.repository.NotificationRepository;
import edu.unipr.eshendetsia.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * Kjo klase sherben per menaxhimin e njoftimeve
 * dhe implementon nderfaqen NotificationService
 */
public class NotificationServiceImplementation implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImplementation(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    /**
     * Ruan nje njoftim ne databaze
     *
     * @param n njoftimi qe do te ruhet
     * @return njoftimi i ruajtur
     */
    public Notification save(Notification n) {
        return notificationRepository.save(n);
    }

    /**
     * Merr te gjitha njoftimet e nje perdoruesi
     *
     * @param userId ID e perdoruesit
     * @return lista e njoftimeve
     */
    public List<Notification> getByUser(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    /**
     * Shenon nje njoftim si te lexuar
     *
     * @param id ID e njoftimit
     */
    public void markAsRead(Long id) {
        Notification n = notificationRepository.findById(id).orElse(null);
        if (n != null) {
            n.setRead(true);
            notificationRepository.save(n);
        }
    }

    /**
     * Fshin nje njoftim nga databaza
     *
     * @param id ID e njoftimit qe do te fshihet
     */
    public void delete(Long id) {
        notificationRepository.deleteById(id);

    }
}