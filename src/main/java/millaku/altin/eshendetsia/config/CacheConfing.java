package millaku.altin.eshendetsia.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Konfigurimi i cache per aplikacionin.
 * Perdor Caffeine si menaxhues te cache per te ruajtur te dhenat ne memorie.
 */
@Configuration
@EnableCaching
public class CacheConfing {

    /**
     * Krijon nje instance te CacheManager me konfigurimet e meposhtme:
     * - Perdor Caffeine si implementim baze
     * - Cache me emer "history"
     * - Te dhenat skadojne pas 1 ore
     * - Madhesia maksimale 1000 elemente
     *
     * @return menaxhuesi i cache i konfiguruar
     */
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("history");
            cacheManager.setCaffeine(Caffeine.newBuilder()
                            .expireAfterWrite(1, TimeUnit.HOURS)
                    .maximumSize(1000));
            return cacheManager;

    }

}
