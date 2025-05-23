package edu.unipr.eshendetsia.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Perscription;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.repository.PerscriptionRepository;
import edu.unipr.eshendetsia.service.interfaces.PerscriptionService;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementimi i sherbimit per menaxhimin e recetave
 */
@AllArgsConstructor
@Service
public class PerscriptionServiceImplementation implements PerscriptionService {
    
    private final PerscriptionRepository persciptionRepository;

    private final UserService userService;

    /**
     * Gjen recetat sipas ID se pacientit
     *
     * @param viewUserId ID e pacientit
     * @return lista e recetave
     * @throws NumberFormatException JWT jo-valide
     * @throws JWTDecodeException JWT jo-valide
     * @throws UnauthorizedException Kur nje user jo-admin tenton te lexoj nje user tjeter
     * @throws NotFoundException Kur useri me id perkatese nu eksiston
     */
    public List<Perscription> findByUserId(Long viewUserId, String authHeader) throws NumberFormatException, JWTDecodeException, UnauthorizedException, NotFoundException {
        Long userId = Long.parseLong(JWT.decode(authHeader).getSubject());

        User user = this.userService.getUserById(userId);

        if (!user.isAdmin() && !user.getId().equals(viewUserId))
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        return this.persciptionRepository.findByUserId(userId);
    }

    /**
     * Gjen recetat sipas ID se mjekut
     *
     * @param doctorId ID e mjekut
     * @return lista e recetave
     * @throws NumberFormatException JWT jo-valide
     * @throws JWTDecodeException JWT jo-valide
     * @throws UnauthorizedException Kur nje user jo-admin tenton te lexoj nje user tjeter
     * @throws NotFoundException Kur useri me id perkatese nuk eksiston
     */
    public List<Perscription> findByDoctorId(Long doctorId, String authHeader) throws NumberFormatException, JWTDecodeException, UnauthorizedException, NotFoundException {
        Long userId = Long.parseLong(JWT.decode(authHeader).getSubject());

        User user = this.userService.getUserById(userId);

        if(!user.isAdmin() && !user.getId().equals(doctorId))
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        return persciptionRepository.findByDoctorId(doctorId);
    }

    /**
     * Ruan receten e re
     *
     * @param prescription receta qe do te ruhet
     * @throws NumberFormatException JWT jo-valide
     * @throws JWTDecodeException JWT jo-valide
     * @throws UnauthorizedException Kur nje user jo-admin tenton te ruaj recete per nje user tjeter
     * @throws NotFoundException Kur useri me id perkatese nu eksiston
     */
    public void save(Perscription prescription, String authHeader) throws NumberFormatException, JWTDecodeException, UnauthorizedException, NotFoundException{
        Long userId = Long.parseLong(JWT.decode(authHeader).getSubject());

        User user = this.userService.getUserById(userId);

        if (!user.isAdmin() && !user.getId().equals(prescription.getDoctor().getId()))
            throw new UnauthorizedException("Nuk jeni i autorizuar");

        persciptionRepository.save(prescription);
    }
}
