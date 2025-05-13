package app.model.clinic;

import app.exception.NotFoundException;
import app.exception.UnauthorizedException;
import app.model.user.UserRepository;
import app.util.JWTUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceImplementation implements ClinicService {

    private final ClinicRepository clinicRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClinicServiceImplementation(ClinicRepository clinicRepository, UserRepository userRepository) {
        this.clinicRepository = clinicRepository;
        this.userRepository = userRepository;
    }

    public List<Clinic> getAllClinics(String authToken) throws UnauthorizedException {

        DecodedJWT jwt = JWTUtil.verifyToken(authToken);
        long jwtSubject = Long.parseLong(jwt.getSubject());

        if (!this.userRepository.getRoleById(jwtSubject).getName().equals("admin"))
            throw new UnauthorizedException();

        return clinicRepository.findAll();
    }

    public Clinic getClinicById(Long clinicId, String authHeader) throws NotFoundException, UnauthorizedException, JWTVerificationException {
        DecodedJWT jwt = JWTUtil.verifyToken(authHeader);
        long jwtSubject = Long.parseLong(jwt.getSubject());

        if (!(this.clinicRepository.getById(clinicId).getDrejtori().getId() == jwtSubject || // is drejtor
                this.userRepository.getRoleById(jwtSubject).getName().equals("admin"))) // is admin
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        Optional<Clinic> validClinic = clinicRepository.findById(clinicId);
        if (validClinic.isEmpty())
            throw new NotFoundException("Klinika nuk eksiston");
        return validClinic.get();
    }

    public void saveClinic(Clinic clinic, String authHeader) throws UnauthorizedException {

        DecodedJWT jwt = JWTUtil.verifyToken(authHeader);
        long jwtSubject = Long.parseLong(jwt.getSubject());

//        !isAdminOrDirector(jwt)
        if (!(this.clinicRepository.getById(clinic.getId()).getDrejtori().getId() == jwtSubject || // is drejtor
                this.userRepository.getRoleById(jwtSubject).getName().equals("admin"))) // is admin
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        clinicRepository.save(clinic);
    }

    public void updateClinic(Long updateClinicId, Clinic updateClinic, String authHeader) throws UnauthorizedException, NotFoundException {
        DecodedJWT jwt = JWTUtil.verifyToken(authHeader);
        long jwtSubject = Long.parseLong(jwt.getSubject());

        if (!(this.clinicRepository.getById(updateClinic.getId()).getDrejtori().getId() == jwtSubject || // is drejtor
                this.userRepository.getRoleById(jwtSubject).getName().equals("admin")))
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        Optional<Clinic> validClinic = clinicRepository.findById(updateClinicId);

        if (validClinic.isEmpty())
            throw new NotFoundException("Klinika nuk u gjet");

        Clinic clinic = validClinic.get();
        clinic.setAddress(updateClinic.getAddress());
        clinic.setEmail(updateClinic.getEmail());
        clinic.setPhone(updateClinic.getPhone());
        clinic.setWebsite(updateClinic.getWebsite());
        clinic.setDrejtori(updateClinic.getDrejtori());

        // qit metod kishim mujt edhe direkt me bo update, pa i metodat e me siperme, po e bojm per me gjujt error, edhe me handle kontrolleri n try/catch
        // errori n qit case gjuhet veq per arsye qe e ka annotations n Cliic.java
        // megjithate, duhet me perdor dto per qita, jo me perdor entitetin si dto
        clinicRepository.save(clinic);
    }

    public void deleteClinic(Long id, String authHeader) {
        DecodedJWT jwt = JWTUtil.verifyToken(authHeader);
        long jwtSubject = Long.parseLong(jwt.getSubject());

        if (!(this.clinicRepository.getById(id).getDrejtori().getId() == jwtSubject ||
                this.userRepository.getRoleById(jwtSubject).getName().equals("admin")))
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        if (!clinicRepository.existsById(id))
            throw new NotFoundException("Klinika nuk eksiston");

        clinicRepository.deleteById(id);
    }
}