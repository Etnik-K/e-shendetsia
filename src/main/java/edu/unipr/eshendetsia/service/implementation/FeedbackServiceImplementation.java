package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.model.entity.Feedback;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.DoctorRepository;
import edu.unipr.eshendetsia.repository.FeedbackRepository;
import edu.unipr.eshendetsia.repository.UserRepository;
import edu.unipr.eshendetsia.service.interfaces.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementimi i sherbimit per menaxhimin e reagimeve
 * nga perdoruesit per doktoret
 */
@AllArgsConstructor
@Service
public class FeedbackServiceImplementation implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    /**
     * Ruan nje reagim te ri ne sistem
     *
     * @param feedback reagimi qe do te ruhet
     *
     */
    public void save(Feedback feedback) {
         feedbackRepository.save(feedback);
    }

    /**
     * Merr te gjitha reagimet per nje doktor specifik
     *
     * @param doctorId identifikuesi i doktorit
     * @return lista e reagimeve
     */
    public List<Feedback> getByDoctorId(Long doctorId, String requestJwt) throws UnauthorizedException, JWTDecodeException, NumberFormatException {
        Long docId = Long.parseLong(JWT.decode(requestJwt).getSubject());

        Optional<Doctor> optDoc = this.doctorRepository.findById(doctorId);
        if (optDoc.isEmpty() && !(docId.equals(doctorId)))
            throw new NotFoundException("Nuk jeni i autorizuar!");

        return feedbackRepository.findByDoctorId(doctorId);
    }

    /**
     * Merr te gjitha reagimet nga nje perdorues specifik
     *
     * @param userId identifikuesi i perdoruesit
     * @return lista e reagimeve
     */
    public List<Feedback> getByUserId(Long userId, String requestJwt) throws UnauthorizedException, JWTDecodeException, NumberFormatException {
        Long uId = Long.parseLong(JWT.decode(requestJwt).getSubject());
        Optional<User> user = this.userRepository.findById(userId);

        if (!(user.get().isAdmin()) && !uId.equals(userId))
            throw new NotFoundException("Nuk jeni i autorizuar!");

        return feedbackRepository.findByUserId(userId);
    }

    /**
     * Fshin nje reagim nga sistemi
     *
     * @param id identifikuesi i reagimit per tu fshire
     */
    public void delete(Long id, String requestJwt) {
        Long uId = Long.parseLong(JWT.decode(requestJwt).getSubject());
        Optional<User> user = this.userRepository.findById(id);

        if (!(user.get().isAdmin()) && !uId.equals(id))
            throw new NotFoundException("Nuk jeni i autorizuar!");
        feedbackRepository.deleteById(id);
    }

}
