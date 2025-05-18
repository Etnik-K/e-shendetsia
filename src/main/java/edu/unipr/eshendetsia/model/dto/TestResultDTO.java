package edu.unipr.eshendetsia.model.dto;

import java.time.LocalDateTime;

/**
 * Klasa qe perfaqeson rezultatin e nje testi mjekesor
 * Perdoret per transferimin e te dhenave te rezultateve te testeve
 */
public class TestResultDTO {
    public Long id;
    public Long userId;
    public Long doctorId;
    public String type;
    public String result;
    public String notes;
    public LocalDateTime timestamp;
}
