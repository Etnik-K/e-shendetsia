package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Notification;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.NotificationRepository;
import edu.unipr.eshendetsia.repository.UserRepository;
import edu.unipr.eshendetsia.service.interfaces.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Kjo klase sherben per menaxhimin e njoftimeve
 * dhe implementon nderfaqen NotificationService
 */
@AllArgsConstructor
@Service
public class NotificationServiceImplementation implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    /**
     * Ruan nje njoftim ne databaze
     *
     * @param n njoftimi qe do te ruhet
     *
     */
    public void save(Notification n, String requestJwt) {
        Long uId = Long.parseLong(JWT.decode(requestJwt).getSubject());
        User user = this.userRepository.getUserById(uId);
        if (!(user.isAdmin()))
            throw new NotFoundException("Nuk jeni i autorizuar!");
        notificationRepository.save(n);
    }

    /**
     * Merr te gjitha njoftimet e nje perdoruesi
     *
     * @param userId ID e perdoruesit
     * @return lista e njoftimeve
     */
    public List<Notification> getByUser(Long userId, String requestJwt) throws UnauthorizedException, JWTDecodeException, NumberFormatException {
        Long uId = Long.parseLong(JWT.decode(requestJwt).getSubject());
        User user = this.userRepository.getUserById(userId);

        if (!(user.isAdmin()) && !uId.equals(userId))
            throw new NotFoundException("Nuk jeni i autorizuar!");
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
    public void delete(Long id, String requestJwt) throws UnauthorizedException, JWTDecodeException, NumberFormatException{
        Long uId = Long.parseLong(JWT.decode(requestJwt).getSubject());
        User user = this.userRepository.getUserById(id);

        if (!(user.isAdmin()) && !uId.equals(id))
            throw new NotFoundException("Nuk jeni i autorizuar!");
        notificationRepository.deleteById(id);

    }
}