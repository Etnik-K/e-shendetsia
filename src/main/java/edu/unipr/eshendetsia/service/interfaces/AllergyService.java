package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.exception.concrete.UnauthorizedException;
import edu.unipr.eshendetsia.model.entity.Allergy;

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
     * @throws JWTVerificationException Nese JWT tokeni nuk eshte valid
     * @throws UnauthorizedException Nese perdoruesi nuk eshte i autorizuar
     */
    void save(Allergy allergy, String authHeader) throws UnauthorizedException, NotFoundException, JWTDecodeException, NumberFormatException;

}
