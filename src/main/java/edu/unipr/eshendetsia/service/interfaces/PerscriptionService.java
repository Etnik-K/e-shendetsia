package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Perscription;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PerscriptionService {

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
    List<Perscription> findByUserId(Long viewUserId, String authHeader) throws NumberFormatException, JWTDecodeException, UnauthorizedException, NotFoundException;

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
    List<Perscription> findByDoctorId(Long doctorId, String authHeader) throws NumberFormatException, JWTDecodeException, UnauthorizedException, NotFoundException;

    /**
     * Ruan receten e re
     *
     * @param prescription receta qe do te ruhet
     * @throws NumberFormatException JWT jo-valide
     * @throws JWTDecodeException JWT jo-valide
     * @throws UnauthorizedException Kur nje user jo-admin tenton te ruaj recete per nje user tjeter
     * @throws NotFoundException Kur useri me id perkatese nu eksiston
     */
    void save(Perscription prescription, String authHeader) throws NumberFormatException, JWTDecodeException, UnauthorizedException, NotFoundException;
}