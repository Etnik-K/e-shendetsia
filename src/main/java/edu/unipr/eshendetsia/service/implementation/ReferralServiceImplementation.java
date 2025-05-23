package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Referral;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.ReferralRepository;
import edu.unipr.eshendetsia.repository.UserRepository;
import edu.unipr.eshendetsia.service.interfaces.ReferralService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementimi i sherbimit per referime mjekesore
 */
@AllArgsConstructor
@Service
public class ReferralServiceImplementation implements ReferralService {
    private final ReferralRepository repository;
    private final UserRepository userRepository;

    /**
     * Ruan nje referim te ri
     *
     * @param referral referimi per tu ruajtur
     *
     */
    public void save(Referral referral, String requestJwt) throws UnauthorizedException, JWTDecodeException {
        Long uId = Long.parseLong(JWT.decode(requestJwt).getSubject());
        User user = this.userRepository.getUserById(uId);
        if (!(user.isAdmin()))
            throw new NotFoundException("Nuk jeni i autorizuar!");

        repository.save(referral);
    }

    /**
     * Merr referimet e nje pacienti
     *
     * @param patientId ID e pacientit
     * @return lista e referimeve
     */
    public List<Referral> getByPatient(Long patientId, String requestJwt) throws UnauthorizedException, JWTDecodeException {
        Long uId = Long.parseLong(JWT.decode(requestJwt).getSubject());
        User user = this.userRepository.getUserById(patientId);

        if (!(user.isAdmin()) && !uId.equals(patientId))
            throw new NotFoundException("Nuk jeni i autorizuar!");

        return repository.findByPatientId(patientId);
    }

    /**
     * Fshin nje referim
     *
     * @param id ID e referimit per tu fshire
     */
    public void delete(Long id, String requestJwt) throws UnauthorizedException, JWTDecodeException {
        Long uId = Long.parseLong(JWT.decode(requestJwt).getSubject());
        User user = this.userRepository.getUserById(uId);

        if (!(user.isAdmin()) && !uId.equals(id))
            throw new NotFoundException("Nuk jeni i autorizuar!");

        repository.deleteById(id);
    }
}
