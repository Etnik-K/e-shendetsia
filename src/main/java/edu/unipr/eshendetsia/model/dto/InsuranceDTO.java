package edu.unipr.eshendetsia.model.dto;

/**
 * Klasa qe permban informacionin e sigurimit
 * shendetesor te pacientit
 */
public class InsuranceDTO {
    public Long id;
    public Long userId;
    public String provider;
    public String policyNumber;
    public String coverageDetails;
    public boolean active;
}
