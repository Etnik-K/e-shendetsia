package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification save(Notification n);

    List<Notification> getByUser(Long userId);

    void markAsRead(Long id);

    void delete(Long id);

}
