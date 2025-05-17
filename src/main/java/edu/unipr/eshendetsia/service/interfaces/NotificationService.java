package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Notification;

import java.util.List;

public interface NotificationService {
    public Notification save(Notification n);
    public List<Notification> getByUser(Long userId);
    public void markAsRead(Long id);
    public void delete(Long id);
}
