package app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    private static final int KEY_SIZE = 256;
    private static final String SECRET_KEY = JWTUtil.generateSecretKey();
    private static final String ISSUER = "e-shendetsia.rks-gov.net";

    /**
     * Krijon një token JWT me pretendimet e dhëna dhe ID-në e përdoruesit
     *
     * @param claims Pretendimet që do të përfshihen në token
     * @param id     ID-ja e përdoruesit
     * @return Token-i i krijuar JWT
     */
    public static String createToken(Map<String, String> claims, long id) {
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
    public static String createToken(long id) {
        return createToken(new HashMap<String, String>(), id);
    }

    /**
     * Verifikon vlefshmerin e një token-i JWT
     *
     * @param token Token-i për tu verifikuar
     * @return Token-i i dekoduar JWT
     * @throws JWTVerificationException Nese token-i eshte i pavlefshëm
     */
    public static DecodedJWT verifyToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }

    /**
     * Merr vleren e nje pretendimi nga token-i
     *
     * @param token     Token-i JWT
     * @param claimName Emri i pretendimit
     * @return Vlera e pretendimit
     */
    public static String getClaimFromToken(String token, String claimName) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getClaim(claimName).asString();
    }

    /**
     * Merr subjektin nga token-i
     *
     * @param token Token-i JWT
     * @return Subjekti i token-it
     */
    public static String getSubjectFromToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getSubject();
    }

    /**
     * Gjeneron nje secret key per nenshkrimin e token-it
     *
     * @return Secrey Key i enkodum ne Base64
     */
    public static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[KEY_SIZE / 8]; // 256 bits = 32 bytes
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key); // Convert to base64 string
    }
}
