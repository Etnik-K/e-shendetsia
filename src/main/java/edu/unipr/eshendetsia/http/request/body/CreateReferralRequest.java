package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.model.entity.Referral;
import edu.unipr.eshendetsia.model.entity.User;

import java.time.LocalDateTime;

public record CreateReferralRequest(
    Long id, Long patientId, Long fromDoctorId, Long toDoctorId, String reason, LocalDateTime referralDate
) {
    public Referral toReferral() {
        User patient = new User();
        Doctor from = new Doctor();
        Doctor to = new Doctor();

        patient.setId(patientId);
        from.setId(fromDoctorId);
        to.setId(toDoctorId);

        return new Referral(id, patient, from, to, reason, referralDate);
    }
}
