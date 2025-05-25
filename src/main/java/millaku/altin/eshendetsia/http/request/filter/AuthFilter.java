package millaku.altin.eshendetsia.http.request.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import millaku.altin.eshendetsia.service.interfaces.JWTService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.List;

@Component
public class AuthFilter implements Filter {

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    private static final List<String> PUBLIC_RESOURCES = List.of(
            "/api/users/login",
            "/users/login",
            "/test",
            "/api/test",
            "/api/users/test",
            "/users/test",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/swagger-ui/**",
            "/swagger-ui/",
            "/v3/api-docs",
            "/v3/api-docs/",
            "/v3/api-docs/**",
            "/favicon.ico",
            "/webjars/**"
    );

    private final JWTService jwtService;

    @Autowired
    public AuthFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {


        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getServletPath();

        if (AuthFilter.PUBLIC_RESOURCES.stream().anyMatch(p -> AuthFilter.pathMatcher.match(p, path))) {
            System.out.println("U plotsu kushti");
            chain.doFilter(request, response);
            return;
        }

        System.out.println("Nuk u plotsu kushti");

        // Authorization header ---
        // --- doket qishtu: "Bearer header.payload.hash"
        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header mungon ose eshte jo-valid");
            return;
        }

        // ja hekim bearer perpara
        // e "ekstraktojm" tokenin
        String token = authHeader.substring("Bearer ".length()).trim();

        try {
            this.jwtService.verifyToken(token);
        } catch (IllegalArgumentException | JWTVerificationException exception) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token jo-valid");
            return;
        }

        chain.doFilter(request, response);
    }
}