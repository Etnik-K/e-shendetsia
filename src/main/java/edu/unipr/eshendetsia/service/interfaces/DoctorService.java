package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> getAllDoctors(String requestJwt) throws UnauthorizedException, JWTDecodeException;
    Doctor getDoctorById(Long id, String requestJwt) throws UnauthorizedException;
    Doctor getDoctorById(Long id) throws UnauthorizedException;
}
