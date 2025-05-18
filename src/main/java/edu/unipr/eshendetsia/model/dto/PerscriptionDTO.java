package edu.unipr.eshendetsia.model.dto;

import edu.unipr.eshendetsia.model.entity.Perscription;

import java.time.LocalDateTime;

/**
 * Klasa qe mundeson transferimin e te dhenave te recetave
 * nga njeri komponent tek tjetri
 */
public class PerscriptionDTO {
    public Long id;
    public String medication;
    public String dosage;
    public String instructions;
    public LocalDateTime issuedAt;
}
