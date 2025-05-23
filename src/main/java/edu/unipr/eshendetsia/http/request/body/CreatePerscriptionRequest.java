package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.model.entity.Perscription;
import edu.unipr.eshendetsia.model.entity.User;

import java.time.LocalDateTime;

public record CreatePerscriptionRequest(
    Long id, Long userId, Long doctorId, String medication, String dosage, String frequency, String durage, LocalDateTime issuedAt
) {
    public Perscription toPerscription() {
        User user = new User();
        Doctor doctor = new Doctor();

        user.setId(userId);
        doctor.setId(doctorId);

        return new Perscription(id, user, doctor, medication, dosage, frequency, durage, issuedAt);
    }
}
