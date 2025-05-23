package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.History;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.HistoryRepository;
import edu.unipr.eshendetsia.service.interfaces.HistoryService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Sherbimi qe implementon logjiken e historise se sistemit
 * dhe menaxhon te dhenat e historise
 */
@AllArgsConstructor
@Service
public class HistoryServiceImplementation implements HistoryService {

    private final HistoryRepository historyRepository;

    private final UserService userService;

    /**
     * Ruan nje rekord te ri ne historine e sistemit
     *
     * @param history rekordi i historise per tu ruajtur
     * @throws UnauthorizedException    nese perdoruesi nuk ka te drejta
     * @throws JWTVerificationException nese ka problem me token
     */
    public void save(History history, String authHeader) throws UnauthorizedException, JWTVerificationException, NotFoundException {
        Long doctorId = Long.parseLong(JWT.decode(authHeader).getSubject());

        User user = this.userService.getUserById(doctorId);

        if (!user.isDoctor() && !user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        this.historyRepository.save(history);
    }
}
