package edu.unipr.eshendetsia.http.request.filter;

import edu.unipr.eshendetsia.model.entity.Role;
import edu.unipr.eshendetsia.service.interfaces.UserService;
import edu.unipr.eshendetsia.util.JWTUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@AllArgsConstructor
@Component
public class AuthFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = request.getHeader("Authorization");

        if (jwtToken == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Nuk ka JWT Token ne header");
            return;
        }

        if (jwtToken.startsWith("Bearer "))
            jwtToken = jwtToken.substring(7);

        DecodedJWT decodedJWT;
        try{
            decodedJWT = this.jwtUtil.verifyToken(jwtToken);
        }catch (JWTVerificationException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, STR."JWT Token i pavlefshem: \{e.getMessage()}");
            return;
        }

        long userId = Long.parseLong(decodedJWT.getSubject());

        Set<Role> userRoles = this.userService.findRolesById(userId);
        request.setAttribute("userRoles", userRoles);

        filterChain.doFilter(request, response);
    }
}