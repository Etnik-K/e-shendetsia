package millaku.altin.eshendetsia.service.interfaces;

import millaku.altin.eshendetsia.model.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification save(Notification n);

    List<Notification> getByUser(Long userId);

    void markAsRead(Long id);

    void delete(Long id);

}
