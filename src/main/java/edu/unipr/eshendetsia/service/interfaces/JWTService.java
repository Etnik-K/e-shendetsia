package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.unipr.eshendetsia.exception.UnauthorizedException;

import java.util.Map;

/**
 * Nderfaqe qe sherben per krijimin dhe verifikimin e tokenave JWT.
 * Mundeson gjenerimin e tokenave te rinj duke perdorur te dhenat e perdoruesit,
 * si dhe verifikon nese nje token ekzistues eshte valid.
 */
public interface JWTService {
    /**
     * Krijon nje token te ri JWT
     *
     * @param claims Te dhenat qe do te ruhen ne token
     * @param id     Identifikatori unik i perdoruesit
     * @return Token-i i krijuar ne forme teksti
     */
    String createToken(Map<String, String> claims, long id) throws IllegalArgumentException, JWTCreationException;

    /**
     * Verifikon vlefshmerin e një token-i JWT
     *
     * @param token Token-i për tu verifikuar
     * @return Token-i i dekoduar JWT
     * @throws JWTVerificationException Nese token-i eshte i pavlefshëm
     */
    DecodedJWT verifyToken(String token) throws JWTVerificationException, IllegalArgumentException;
}
