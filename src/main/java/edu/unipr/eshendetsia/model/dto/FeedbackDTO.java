package edu.unipr.eshendetsia.model.dto;

import java.time.LocalDateTime;

public class FeedbackDTO {
    public Long id;
    public Long userId;
    public Long doctorId;
    public String message;
    public int rating;
    public LocalDateTime submittedAt;
}
