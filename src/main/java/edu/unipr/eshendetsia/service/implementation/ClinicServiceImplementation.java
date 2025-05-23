package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.InternalServerErrorException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Clinic;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.ClinicRepository;
import edu.unipr.eshendetsia.service.interfaces.ClinicService;
import com.auth0.jwt.JWT;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementimi i sherbimit per menaxhimin e klinikave mjekesore.
 * Sherben si nderlidhes mes shtresave te kontrollerit dhe repositories.
 */
@AllArgsConstructor
@Service
public class ClinicServiceImplementation implements ClinicService {

    private final ClinicRepository clinicRepository;

    private final UserService userService;

    /**
     * Merr listen e te gjitha klinikave ne sistem.
     *
     * @param authToken tokeni i autentifikimit
     * @return lista e klinikave
     * @throws UnauthorizedException nese perdoruesi nuk eshte admin
     * @throws JWTDecodeException JWT jo-valid
     * @throws NumberFormatException JWT jo-valid
     */
    public List<Clinic> getAllClinics(String authToken) throws UnauthorizedException, JWTDecodeException, NumberFormatException {
        Long jwtSubject = Long.parseLong(JWT.decode(authToken).getSubject());

        User user = this.userService.getUserById(jwtSubject);

        if (!user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        return clinicRepository.findAll();
    }

    /**
     * Merr kliniken sipas ID-se.
     *
     * @param clinicId   ID e klinikes
     * @param authHeader tokeni i autentifikimit
     * @return klinika e kerkuar
     * @throws UnauthorizedException nese perdoruesi nuk eshte admin ose drejtor i klinikes
     */
    public Clinic getClinicById(Long clinicId, String authHeader) throws UnauthorizedException, JWTDecodeException, NumberFormatException, NotFoundException {
        Long jwtSubject = Long.parseLong(JWT.decode(authHeader).getSubject());

        Optional<Clinic> optClinic = this.clinicRepository.findById(clinicId);

        User user = this.userService.getUserById(jwtSubject);

        if (optClinic.isEmpty())
            throw new InternalServerErrorException("Oops, dicka shkoi gabim");

        Clinic clinic = optClinic.get();
        User drejtori = clinic.getDrejtori();

        if (!user.isAdmin() && !user.equals(drejtori))
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        return clinic;
    }

    /**
     * Ruan te dhenat e klinikes ne sistem.
     *
     * @param clinic     klinika per tu ruajtur
     * @param authHeader tokeni i autentifikimit
     * @throws UnauthorizedException nese perdoruesi nuk eshte admin ose drejtor i klinikes
     */
    public void saveClinic(Clinic clinic, String authHeader) throws UnauthorizedException, JWTDecodeException, NumberFormatException, NotFoundException {
        Long jwtSubject = Long.parseLong(JWT.decode(authHeader).getSubject());

        Optional<Clinic> optClinic = this.clinicRepository.findById(clinic.getId());

        User user = this.userService.getUserById(jwtSubject);

        if (optClinic.isEmpty())
            throw new InternalServerErrorException("Oops, dicka shkoi gabim");

        Clinic validClinic = optClinic.get();

        if (!user.equals(validClinic.getDrejtori()) && // is drejtor
                !user.isAdmin()) // isadmin
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        clinicRepository.save(clinic);
    }

    /**
     * Perditeson te dhenat e klinikes.
     *
     * @param updateClinic   klinika me te dhenat e reja
     * @param authHeader     tokeni i autentifikimit
     * @throws UnauthorizedException nese perdoruesi nuk eshte admin ose drejtor i klinikes
     */
    public void updateClinic(Clinic updateClinic, String authHeader) throws UnauthorizedException, JWTDecodeException, NumberFormatException, InternalServerErrorException{
        Long jwtSubject = Long.parseLong(JWT.decode(authHeader).getSubject());

        User user = this.userService.getUserById(jwtSubject);

        Optional<Clinic> optClinic = clinicRepository.findById(updateClinic.getId());

        if (optClinic.isEmpty())
            throw new InternalServerErrorException("Oops, dicka shkoi gabim");

        Clinic clinic = optClinic.get();

        if (!user.equals(clinic.getDrejtori()) &&
                !user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        clinic.setAddress(updateClinic.getAddress());
        clinic.setEmail(updateClinic.getEmail());
        clinic.setPhone(updateClinic.getPhone());
        clinic.setWebsite(updateClinic.getWebsite());
        clinic.setDrejtori(updateClinic.getDrejtori());

        clinicRepository.save(clinic);
    }

    /**
     * Fshin kliniken nga sistemi.
     *
     * @param id         ID e klinikes per tu fshire
     * @param authHeader tokeni i autentifikimit
     * @throws UnauthorizedException nese perdoruesi nuk eshte admin ose drejtor i klinikes
     * @throws NotFoundException     nese klinika nuk gjendet
     */
    public void deleteClinic(Long id, String authHeader) throws UnauthorizedException, NotFoundException, JWTDecodeException, NumberFormatException, InternalServerErrorException {
        Long jwtSubject = Long.parseLong(JWT.decode(authHeader).getSubject());

        Optional<Clinic> validClinic = clinicRepository.findById(id);

        User user = this.userService.getUserById(jwtSubject);

        if (validClinic.isEmpty())
            throw new InternalServerErrorException("Oops, dicka shkoi gabim");

        Clinic clinic = validClinic.get();

        if (!user.equals(clinic.getDrejtori()) &&
                !user.isAdmin())
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        if (!clinicRepository.existsById(id))
            throw new NotFoundException("Klinika nuk eksiston");

        clinicRepository.deleteById(id);
    }
}