package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Feedback;

import java.util.List;

public interface FeedbackService {

    void save(Feedback feedback);

    List<Feedback> getByDoctorId(Long doctorId, String requestJwt) throws UnauthorizedException, JWTDecodeException, NumberFormatException;

    List<Feedback> getByUserId(Long userId, String requestJwt) throws UnauthorizedException, JWTDecodeException, NumberFormatException;

    void delete(Long id, String requestJwt) throws UnauthorizedException;

}
