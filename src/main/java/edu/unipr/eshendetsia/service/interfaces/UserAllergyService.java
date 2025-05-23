package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.UserAllergy;

import java.util.List;

public interface UserAllergyService {

    /**
     * Merr te gjitha alergjite e nje perdoruesi
     *
     * @param allergyUserId ID e perdoruesit
     * @return lista e alergjive
     * @throws UnauthorizedException Nese perdoruesi nuk eshte i autorizuar
     * @throws NotFoundException     Nese alergjia nuk eshte gjetur
     */
    List<UserAllergy> getByUserId(Long allergyUserId, String authHeader) throws UnauthorizedException, NotFoundException;

    /**
     * Fshin nje alergji te userit nga sistemi
     *
     * @param id ID e alergjise per tu fshire
     * @throws JWTVerificationException Nese JWT tokeni nuk eshte valid
     * @throws UnauthorizedException    Nese perdoruesi nuk eshte i autorizuar
     * @throws NotFoundException        Nese alergjia nuk eshte gjetur
     */
    void delete(Long id, String authHeader) throws UnauthorizedException, NotFoundException;
}