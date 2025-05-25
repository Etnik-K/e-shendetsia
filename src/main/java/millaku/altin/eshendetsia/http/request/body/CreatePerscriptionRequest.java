package millaku.altin.eshendetsia.http.request.body;

import millaku.altin.eshendetsia.model.entity.Doctor;
import millaku.altin.eshendetsia.model.entity.Perscription;
import millaku.altin.eshendetsia.model.entity.User;

import java.time.LocalDateTime;

public record CreatePerscriptionRequest(
    Long id,
    Long userId,
    Long doctorId,
    String medication,
    String dosage,
    String frequency,
    String durage,
    LocalDateTime issuedAt
) {
    public Perscription toPerscription() {
        User user = new User();
        user.setId(userId);

        Doctor doctor = new Doctor();
        doctor.setId(doctorId);

        return new Perscription(id, user, doctor, medication, dosage, frequency, durage, issuedAt);
    }
}
