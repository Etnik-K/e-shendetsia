package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Konfiguron Cross-Origin Resource Sharing (CORS) per aplikacionin.
 * Lejon kontrollin e aksesit te burimeve nga domene te ndryshme.
 */
@Configuration
public class CorsConfig {
    /**
     * Krijon nje konfigurim CORS qe:
     * - Lejon te gjitha rruget ("/**")
     * - Lejon akses nga localhost:3000 (React)
     * - Lejon metodat GET, POST, PUT, DELETE, OPTIONS
     * - Lejon te gjitha headers
     *
     * @return konfiguruesi i CORS
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // allow all paths
                        .allowedOrigins("http://localhost:3000") // allow React
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}