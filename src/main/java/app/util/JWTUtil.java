package app.util;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

public interface JWTUtil {
    String createToken(Map<String, String> claims, long id);
    DecodedJWT verifyToken(String token);
}
