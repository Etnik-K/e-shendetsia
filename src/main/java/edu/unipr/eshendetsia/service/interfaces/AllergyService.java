package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Allergy;

import java.util.List;

/**
 * Interface e sherbimit per menaxhimin e alergjive
 * perfshin logjiken kryesore te biznesit per te gjitha veprimet
 * qe lidhen me alergji
 */
public interface AllergyService {

    /**
     * Ruan nje alergji te re ne sistem
     *
     * @param allergy objekti i alergjise per tu ruajtur
     * @return alergji e ruajtur
     * @throws JWTVerificationException Nese JWT tokeni nuk eshte valid
     * @throws UnauthorizedException Nese perdoruesi nuk eshte i autorizuar
     */
    Allergy save(Allergy allergy, String authHeader) throws JWTVerificationException, UnauthorizedException;

    /**
     * Merr te gjitha alergjet e nje perdoruesi
     *
     * @param userId ID e perdoruesit
     * @return lista e alergjive
     * @throws JWTVerificationException Nese JWT tokeni nuk eshte valid
     * @throws UnauthorizedException Nese perdoruesi nuk eshte i autorizuar
     * @throws NotFoundException Nese alergjia nuk eshte gjetur
     */
    List<Allergy> getByUserId(Long userId, String authHeader) throws JWTVerificationException, UnauthorizedException, NotFoundException;

    /**
     * Fshin nje alergji nga sistemi
     *
     * @param id ID e alergjise per tu fshire
     * @throws JWTVerificationException Nese JWT tokeni nuk eshte valid
     * @throws UnauthorizedException Nese perdoruesi nuk eshte i autorizuar
     * @throws NotFoundException Nese alergjia nuk eshte gjetur
     */
    void delete(Long id, String authHeader) throws JWTVerificationException, UnauthorizedException, NotFoundException;
}
