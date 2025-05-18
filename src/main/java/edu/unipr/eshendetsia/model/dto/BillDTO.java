package edu.unipr.eshendetsia.model.dto;

import java.time.LocalDateTime;

/**
 * Klasa qe perfaqson nje fature ne sistem
 */
public class BillDTO {
    public Long id;
    public Long userId;
    public Double amount;
    public String description;
    public boolean isPaid;
    public LocalDateTime issuedAt;
}
