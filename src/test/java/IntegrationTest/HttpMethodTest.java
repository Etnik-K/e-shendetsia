package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import edu.unipr.eshendetsia.model.entity.HttpMethod;

/**
 * Klasa per testimin e metodave HTTP.
 * Teston vlerat, numrin, renditjen dhe vlefshmerin e metodave HTTP.
 */
class HttpMethodTest {

    /**
     * Teston nese vlerat e metodave HTTP jane te sakta
     */
    @Test
    void testHttpMethodValues() {
        assertEquals(HttpMethod.GET, HttpMethod.valueOf("GET"));
        assertEquals(HttpMethod.POST, HttpMethod.valueOf("POST"));
        assertEquals(HttpMethod.PUT, HttpMethod.valueOf("PUT"));
        assertEquals(HttpMethod.PATCH, HttpMethod.valueOf("PATCH"));
        assertEquals(HttpMethod.DELETE, HttpMethod.valueOf("DELETE"));
    }

    /**
     * Teston numrin total te metodave HTTP
     */
    @Test
    void testHttpMethodCount() {
        assertEquals(5, HttpMethod.values().length);
    }

    /**
     * Teston renditjen e metodave HTTP
     */
    @Test
    void testHttpMethodOrdinal() {
        assertEquals(0, HttpMethod.GET.ordinal());
        assertEquals(1, HttpMethod.POST.ordinal());
        assertEquals(2, HttpMethod.PUT.ordinal());
        assertEquals(3, HttpMethod.PATCH.ordinal());
        assertEquals(4, HttpMethod.DELETE.ordinal());
    }

    /**
     * Teston sjelljen e metodave HTTP per vlera jo te vlefshme
     */
    @Test
    void testHttpMethodValueOf() {
        assertThrows(IllegalArgumentException.class, () -> HttpMethod.valueOf("INVALID"));
    }
}