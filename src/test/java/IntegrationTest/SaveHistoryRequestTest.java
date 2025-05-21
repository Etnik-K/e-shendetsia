package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import edu.unipr.eshendetsia.http.request.SaveHistoryRequest;
import edu.unipr.eshendetsia.model.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class SaveHistoryRequestTest {
    private SaveHistoryRequest request;
    private LocalDateTime testDate;

    @BeforeEach
    void setUp() {
        testDate = LocalDateTime.now();
        request = new SaveHistoryRequest(
                1L, 2L, 3L, "Test Description",
                "Test Diagnosis", "Test Treatment", testDate
        );
    }

    @Test
    void shouldConvertToHistory() {
        History history = request.toHistory();

        assertAll(
                () -> assertEquals(request.id(), history.getId()),
                () -> assertEquals(request.userId(), history.getUserId()),
                () -> assertEquals(request.doctorId(), history.getDoctorId()),
                () -> assertEquals(request.description(), history.getDescription()),
                () -> assertEquals(request.diagnosis(), history.getDiagnosis()),
                () -> assertEquals(request.treatment(), history.getTreatment()),
                () -> assertEquals(request.date(), history.getDate())
        );
    }
}