package app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtilImplementation implements JWTUtil{

    private static final int KEY_SIZE = 256;
    private static final String SECRET_KEY = JWTUtilImplementation.generateSecretKey();
    private static final String ISSUER = "e-shendetsia.rks-gov.net";

    /**
     * Krijon një token JWT me claims-at e dhëna dhe ID-në e përdoruesit
     *
     * @param claims claims-at që do të përfshihen në token
     * @param id     ID-ja e përdoruesit
     * @return Token-i i krijuar JWT
     */
    public String createToken(Map<String, String> claims, long id) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        Date expirationDate = new Date(System.currentTimeMillis() + 3600000);

        JWTCreator.Builder builder = JWT.create()
                .withIssuer(ISSUER)
                .withSubject(String.valueOf(id))
                .withExpiresAt(expirationDate);

        for (Map.Entry<String, String> entry : claims.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue());
        }

        return builder.sign(algorithm);
    }

    /**
     * Krijon një token JWT me ID-në e perdoruesit
     *
     * @param id ID-ja e perdoruesit
     * @return Token-i i krijuar JWT
     */
    public String createToken(long id) {
        return createToken(new HashMap<>(), id);
    }

    /**
     * Verifikon vlefshmerin e një token-i JWT
     *
     * @param token Token-i për tu verifikuar
     * @return Token-i i dekoduar JWT
     * @throws JWTVerificationException Nese token-i eshte i pavlefshëm
     */
    public DecodedJWT verifyToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }

    /**
     * Merr vleren e nje claim nga token-i
     *
     * @param token Token-i JWT
     * @param claimName Emri(key) i claim-it
     * @return Vlera e claim
     */
    public String getClaimFromToken(String token, String claimName) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getClaim(claimName).asString();
    }

    /**
     * Merr subjektin nga token-i
     *
     * @param token Token-i JWT
     * @return Subjekti i token-it
     */
    public String getSubjectFromToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getSubject();
    }

    /**
     * Gjeneron nje secret key per nenshkrimin e token-it.
     * Thirret sa her te niset serveri. Nese serveri ristartohet, secret key ka me ndrru, d.m.th. krejt JWT tokenat bohetn te pavlefshem.
     *
     * @return Secrey Key i enkodum ne Base64
     */
    private static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[KEY_SIZE / 8]; // 256 bits = 32 bytes
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key); // Convert to base64 string
    }
}
