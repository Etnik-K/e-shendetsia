package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Appointment;

public interface AppointmentService {

    /**
     * Ruan terminin ne sistem
     *
     * @param appointment termini per tu ruajtur
     * @return termini i ruajtur
     */
    Appointment save(Appointment appointment, String requestJwt) throws JWTDecodeException, UnauthorizedException, NotFoundException, NumberFormatException;

    /**
     * Anulon terminin me ID te caktuar
     *
     * @param id identifikuesi i terminit per tu anuluar
     */
    void cancel(Long id, String requestJwt) throws UnauthorizedException, JWTDecodeException, NotFoundException, NumberFormatException;
}
