package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import edu.unipr.eshendetsia.model.HttpMethod;

class HttpMethodTest {

    @Test
    void testHttpMethodValues() {
        assertEquals(HttpMethod.GET, HttpMethod.valueOf("GET"));
        assertEquals(HttpMethod.POST, HttpMethod.valueOf("POST"));
        assertEquals(HttpMethod.PUT, HttpMethod.valueOf("PUT"));
        assertEquals(HttpMethod.PATCH, HttpMethod.valueOf("PATCH"));
        assertEquals(HttpMethod.DELETE, HttpMethod.valueOf("DELETE"));
    }

    @Test
    void testHttpMethodCount() {
        assertEquals(5, HttpMethod.values().length);
    }

    @Test
    void testHttpMethodOrdinal() {
        assertEquals(0, HttpMethod.GET.ordinal());
        assertEquals(1, HttpMethod.POST.ordinal());
        assertEquals(2, HttpMethod.PUT.ordinal());
        assertEquals(3, HttpMethod.PATCH.ordinal());
        assertEquals(4, HttpMethod.DELETE.ordinal());
    }

    @Test
    void testHttpMethodValueOf() {
        assertThrows(IllegalArgumentException.class, () -> HttpMethod.valueOf("INVALID"));
    }
}