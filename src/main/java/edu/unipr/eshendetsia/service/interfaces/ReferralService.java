package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Referral;

import java.util.List;

public interface ReferralService {

    void save(Referral referral, String requestJwt) throws UnauthorizedException, JWTDecodeException;

    List<Referral> getByPatient(Long patientId, String requestJwt) throws UnauthorizedException, JWTDecodeException;

    void delete(Long id, String requestJwt) throws UnauthorizedException, JWTDecodeException;

}