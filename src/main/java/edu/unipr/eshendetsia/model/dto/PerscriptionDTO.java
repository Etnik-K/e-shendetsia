package edu.unipr.eshendetsia.model.dto;

import edu.unipr.eshendetsia.model.entity.Perscription;

import java.time.LocalDateTime;

public class PerscriptionDTO {
    public Long id;
    public String medication;
    public String dosage;
    public String instructions;
    public LocalDateTime issuedAt;
}
