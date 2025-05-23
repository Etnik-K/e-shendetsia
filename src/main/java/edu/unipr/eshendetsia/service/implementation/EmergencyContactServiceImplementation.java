package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.EmergencyContactRepository;
import edu.unipr.eshendetsia.service.interfaces.EmergencyContactService;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import edu.unipr.eshendetsia.model.entity.EmergencyContact;

import java.util.List;

/**
 * Implementimi i sherbimit per kontaktet emergjente
 * Menaxhon operacionet CRUD per kontaktet emergjente
 */
@AllArgsConstructor
@Service
public class EmergencyContactServiceImplementation implements EmergencyContactService{

    private final UserService userService;
    private final EmergencyContactRepository repository;

    /**
     * Ruan nje kontakt emergjent ne sistem
     *
     * @param contact kontakti emergjent per tu ruajtur
     * @return kontakti i ruajtur
     */
    public EmergencyContact save(EmergencyContact contact) {
        return repository.save(contact);
    }

    /**
     * Merr listen e kontakteve emergjente per nje perdorues
     *
     * @param userId ID e perdoruesit
     * @return lista e kontakteve emergjente
     */
        public List<EmergencyContact> getByUserId(Long userId, String requestJwt) throws UnauthorizedException, JWTDecodeException, NumberFormatException{

        Long userJwt = Long.parseLong(JWT.decode(requestJwt).getSubject());
        User user = this.userService.getUserById(userJwt);

        if (!user.isAdmin() && !(userJwt.equals(userId)))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");
        return repository.findByUserId(userId);
    }

    /**
     * Fshin nje kontakt emergjent nga sistemi
     *
     * @param id ID e kontaktit per tu fshire
     */
    public void delete(Long id, String requestJwt, Long userId) throws UnauthorizedException, JWTDecodeException, NumberFormatException{
        DecodedJWT jwt = JWT.decode(requestJwt);
        Long jwtSubject = Long.valueOf(jwt.getSubject());

        User user = this.userService.getUserById(userId);

        if (!user.isAdmin() && !jwtSubject.equals(userId))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        repository.deleteById(id);
        }
    }
