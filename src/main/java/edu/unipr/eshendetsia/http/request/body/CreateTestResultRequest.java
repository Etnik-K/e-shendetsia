package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.TestResult;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public record CreateTestResultRequest(
    Long id, Long userId, Long doctorId, String type, String result, String notes, LocalDateTime timestamp
) {
    public TestResult toTestResult() {
        return new TestResult(id, userId, doctorId, type, result, notes, timestamp);
    }
}
