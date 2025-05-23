package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    void save(Notification n, String requestJwt);

    List<Notification> getByUser(Long userId, String requestJwt) throws UnauthorizedException, JWTDecodeException, NumberFormatException;

    void markAsRead(Long id);

    void delete(Long id, String requestJwt) throws UnauthorizedException, JWTDecodeException, NumberFormatException;

}
