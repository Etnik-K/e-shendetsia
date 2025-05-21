package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.Referral;

import java.time.LocalDateTime;

public record CreateReferralRequest(
    Long id, Long patientId, Long fromDoctorId, Long toDoctorId, String reason, LocalDateTime referralDate
) {
    public Referral toReferral() {
        return new Referral(id, patientId, fromDoctorId, toDoctorId, reason, referralDate);
    }
}
