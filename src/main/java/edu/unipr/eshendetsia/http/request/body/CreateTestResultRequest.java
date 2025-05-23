package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.model.entity.TestResult;
import edu.unipr.eshendetsia.model.entity.User;

import java.time.LocalDateTime;

public record CreateTestResultRequest(
    Long id, Long userId, Long doctorId, String type, String result, String notes, LocalDateTime timestamp
) {
    public TestResult toTestResult() {
        User user = new User();
        Doctor doctor = new Doctor();

        user.setId(userId);
        doctor.setId(doctorId);

        return new TestResult(id, user, doctor, type, result, notes, timestamp);
    }
}
