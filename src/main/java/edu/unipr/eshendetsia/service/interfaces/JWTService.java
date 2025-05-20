package edu.unipr.eshendetsia.service.interfaces;

import com.auth0.jwt.interfaces.DecodedJWT;

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
    String createToken(Map<String, String> claims, long id);

    /**
     * Verifikon nese nje token JWT eshte valid
     *
     * @param token Token-i qe do te verifikohet
     * @return Objekti DecodedJWT qe permban te dhenat e token-it
     */
    DecodedJWT verifyToken(String token);
}
