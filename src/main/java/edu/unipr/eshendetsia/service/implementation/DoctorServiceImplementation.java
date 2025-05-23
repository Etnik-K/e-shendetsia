package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.repository.DoctorRepository;
import edu.unipr.eshendetsia.service.interfaces.DoctorService;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementimi i sherbimit te doktorit qe perfshin
 * operacionet themelore per menaxhimin e doktoreve
 */
@Service
public class DoctorServiceImplementation implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserService userService;

    /**
     * Konstruktori i klases
     *
     * @param doctorRepository repository per qasje ne te dhenat e doktoreve
     */
    @Autowired
    public DoctorServiceImplementation(DoctorRepository doctorRepository, UserService userService) {
        this.doctorRepository = doctorRepository;
        this.userService = userService;
    }

    /**
     * Merr listen e te gjithe doktoreve
     *
     * @return lista e doktoreve ne sistem
     */
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    /**
     * Gjen doktorin sipas identifikuesit
     *
     * @param viewUserId identifikuesi i doktorit
     * @return doktori i gjetur ose Optional bosh
     */
    public Doctor getDoctorById(Long viewUserId, String authHeader)
            throws UnauthorizedException, NumberFormatException, NotFoundException
            {
        Long requestUserId = Long.parseLong(JWT.decode(authHeader).getSubject());

        if (!requestUserId.equals(viewUserId) &&
                !this.userService.getUserById(requestUserId).isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        Optional<Doctor> doctor = this.doctorRepository.findById(viewUserId);
        if (doctor.isEmpty())
            throw new NotFoundException("Nuk u gjet doktori!");

        return doctor.get();
    }

}