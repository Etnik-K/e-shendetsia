package edu.unipr.eshendetsia.model.dto;

import java.time.LocalDateTime;

/**
 * Klasa qe perfaqson nje takim mjekesor
 */
public class AppointmentDTO {
    public Long id;
    public Long userId;
    public Long doctorId;
    public LocalDateTime appointmentTime;
    public String reason;
    public String status;
}
