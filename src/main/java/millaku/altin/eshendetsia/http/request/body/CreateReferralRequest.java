package millaku.altin.eshendetsia.http.request.body;

import millaku.altin.eshendetsia.model.entity.Doctor;
import millaku.altin.eshendetsia.model.entity.Referral;
import millaku.altin.eshendetsia.model.entity.User;

import java.time.LocalDateTime;

public record CreateReferralRequest(
    Long id,
    Long patientId,
    Long fromDoctorId,
    Long toDoctorId,
    String reason,
    LocalDateTime referralDate
) {
    public Referral toReferral() {
        User patient = new User();
        patient.setId(patientId);

        Doctor fromDoctor = new Doctor();
        fromDoctor.setId(fromDoctorId);

        Doctor toDoctor = new Doctor();
        toDoctor.setId(toDoctorId);

        return new Referral(id, patient, fromDoctor, toDoctor, reason, referralDate);
    }
}
