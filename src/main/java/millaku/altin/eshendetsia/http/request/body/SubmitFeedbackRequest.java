package millaku.altin.eshendetsia.http.request.body;

import millaku.altin.eshendetsia.model.entity.Doctor;
import millaku.altin.eshendetsia.model.entity.Feedback;
import millaku.altin.eshendetsia.model.entity.User;

import java.time.LocalDateTime;

public record SubmitFeedbackRequest (
    Long id,
    Long userId,
    Long doctorId,
    String feedback,
    Integer rating,
    LocalDateTime issuedAt
) {
    public Feedback toFeedback() {
        User user = new User();
        user.setId(userId);

        Doctor doctor = new Doctor();
        doctor.setId(doctorId);

        return new Feedback(id, user, doctor, feedback, rating, issuedAt);
    }
}
