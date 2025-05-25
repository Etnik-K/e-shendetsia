package millaku.altin.eshendetsia.http.request.body;

import millaku.altin.eshendetsia.model.entity.Doctor;
import millaku.altin.eshendetsia.model.entity.TestResult;
import millaku.altin.eshendetsia.model.entity.User;

import java.time.LocalDateTime;

public record CreateTestResultRequest(
    Long id,
    Long userId,
    Long doctorId,
    String type,
    String result,
    String notes,
    LocalDateTime timestamp
) {
    public TestResult toTestResult() {
        User user = new User();
        user.setId(userId);

        Doctor doctor = new Doctor();
        doctor.setId(doctorId);

        return new TestResult(id, user, doctor, type, result, notes, timestamp);
    }
}
