package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.Feedback;

import java.time.LocalDateTime;

public record SubmitFeedbackRequest (
        Long id, Long userId, Long doctorId, String feedback, Integer rating, LocalDateTime issuedAt
) {
    public Feedback toFeedback() {
        return new Feedback(id, userId, doctorId, feedback, rating, issuedAt);
    }
}
