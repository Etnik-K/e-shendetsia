package edu.unipr.eshendetsia.model.dto;

import java.time.LocalDateTime;

public class NotificationDTO {
    public Long id;
    public String message;
    public boolean isRead;
    public LocalDateTime timestamp;
}
