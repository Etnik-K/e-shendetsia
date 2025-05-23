package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.model.entity.Feedback;

import java.time.LocalDateTime;

public record SubmitFeedbackRequest (
        Long id, Long userId, Long doctorId, String feedback, Integer rating, LocalDateTime issuedAt
) {
    public Feedback toFeedback() {
        
        User user = new User();
        Doctor doctor = new Doctor();

        user.setId(userId);
        doctor.setId(doctorId);
        
        return new Feedback(id, user, doctor, feedback, rating, issuedAt);
    }
}
