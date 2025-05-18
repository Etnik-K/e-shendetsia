package edu.unipr.eshendetsia.model.dto;

import java.time.LocalDateTime;

public class ReferralDTO {
    public Long id;
    public Long patientId;
    public Long fromDoctorId;
    public Long toDoctorId;
    public String reason;
    public LocalDateTime referralDate;
}
