package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Appointment;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.model.enums.AppointmentStatus;
import edu.unipr.eshendetsia.repository.AppointmentRepository;
import edu.unipr.eshendetsia.service.interfaces.AppointmentService;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementimi i sherbimit per menaxhimin e termineve
 */
@AllArgsConstructor
@Service
public class AppointmentServiceImplementation implements AppointmentService {

    private final UserService userService;

    private final AppointmentRepository appointmentRepository;

    /**
     * Ruan terminin ne sistem
     *
     * @param appointment termini per tu ruajtur
     * @return termini i ruajtur
     */
    public Appointment save(Appointment appointment, String requestJwt) throws JWTDecodeException, UnauthorizedException, NotFoundException, NumberFormatException {
        Long requestUserId = Long.parseLong(JWT.decode(requestJwt).getSubject());

        User user = this.userService.getUserById(requestUserId);

        if (!user.isAdmin() && !user.equals(appointment.getUser()))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        return appointmentRepository.save(appointment);
    }

    /**
     * Anulon terminin me ID te caktuar
     *
     * @param id identifikuesi i terminit per tu anuluar
     */
    public void cancel(Long id, String requestJwt) throws UnauthorizedException, JWTDecodeException, NotFoundException, NumberFormatException {
        Long requestUserId = Long.parseLong(JWT.decode(requestJwt).getSubject());
        User user = this.userService.getUserById(requestUserId);

        Optional<Appointment> optAppointment = this.appointmentRepository.findById(id);

        if (optAppointment.isEmpty())
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        Appointment appointment = optAppointment.get();

        if (!user.isAdmin() && !user.equals(appointment.getUser()))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        appointment.setStatus(AppointmentStatus.CANCELLED);
        this.appointmentRepository.save(appointment);
    }

}
