package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.EmergencyContact;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyContactService {

    EmergencyContact save(EmergencyContact contact);

    List<EmergencyContact> getByUserId(Long userId, String requestJwt) throws UnauthorizedException, JWTDecodeException;

    void delete(Long id, String requestJwt, Long userId) throws UnauthorizedException, JWTDecodeException;

}
