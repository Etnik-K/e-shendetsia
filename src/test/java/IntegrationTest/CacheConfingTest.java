package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import edu.unipr.eshendetsia.config.CacheConfing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = CacheConfing.class)
class CacheConfingTest {

    @Autowired
    private CacheManager cacheManager;

    @Test
    void testCacheManagerConfiguration() {
        assertNotNull(cacheManager, "Cache manager should not be null");
        assertTrue(cacheManager instanceof CaffeineCacheManager, "Cache manager should be instance of CaffeineCacheManager");

        CaffeineCacheManager caffeineCacheManager = (CaffeineCacheManager) cacheManager;
        assertNotNull(caffeineCacheManager.getCache("history"), "History cache should exist");
    }
}