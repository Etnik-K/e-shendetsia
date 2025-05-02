package app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JWTUtil {

    private static final int KEY_SIZE = 256;
    private static final String SECRET_KEY = JWTUtil.generateSecretKey();
    private static final String ISSUER = "e-shendetsia.rks-gov.net";

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

    public static DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }

    public static String getClaimFromToken(String token, String claimName) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getClaim(claimName).asString();
    }

    public static String getSubjectFromToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getSubject();
    }

    public static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[KEY_SIZE / 8]; // 256 bits = 32 bytes
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key); // Convert to base64 string
    }
}
