package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.DoctorRepository;
import edu.unipr.eshendetsia.repository.UserRepository;
import edu.unipr.eshendetsia.service.interfaces.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

/**
 * Implementimi i sherbimit te doktorit qe perfshin
 * operacionet themelore per menaxhimin e doktoreve
 */
@AllArgsConstructor
@Service
public class DoctorServiceImplementation implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    /**
     * Merr listen e te gjithe doktoreve
     *
     * @return lista e doktoreve ne sistem
     */
    public List<Doctor> getAllDoctors(String requestJwt) throws UnauthorizedException, JWTDecodeException {
        Long doctorId = Long.parseLong(JWT.decode(requestJwt).getSubject());

        Optional<User> user = this.userRepository.findById(doctorId);
        if (user.isEmpty())
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        if (user.get().getRoles().stream().noneMatch(role -> role.getName().equalsIgnoreCase("admin")))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        return doctorRepository.findAll();
    }

    /**
     * Gjen doktorin sipas identifikuesit
     *
     * @param id identifikuesi i doktorit
     * @return doktori i gjetur ose Optional bosh
     */
    public Doctor getDoctorById(Long id, String requestJwt) throws UnauthorizedException, JWTDecodeException, NumberFormatException {
        Long doctorId = Long.parseLong(JWT.decode(requestJwt).getSubject());
        Optional<Doctor> optDoctor = this.doctorRepository.findById(doctorId);

        if (optDoctor.isEmpty() || !(doctorId.equals(id)))
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        return optDoctor.get();
    }

    /**
     * kjo eshte nje metode qe spo kom kohe me dokumentu
     * @param id hajt se e din ti ma mir se un
     * @return ishalla na len profa 10
     * @throws UnauthorizedException KLM
     */
    @Override
    public Doctor getDoctorById(Long id) throws UnauthorizedException {
        Optional<Doctor> optDoctor = this.doctorRepository.findById(id);

        if (optDoctor.isEmpty())
            throw new UnauthorizedException("Nuk jeni i autorizuar!");

        return optDoctor.get();
    }

}