package millaku.altin.eshendetsia.http.request.body;

import millaku.altin.eshendetsia.model.entity.Doctor;
import millaku.altin.eshendetsia.model.entity.History;
import millaku.altin.eshendetsia.model.entity.User;

import java.time.LocalDateTime;

public record SaveHistoryRequest(
    Long id, Long userId, Long doctorId, String description, String diagnosis, String treatment, LocalDateTime date
) {
    public History toHistory() {
        User user = new User();
        user.setId(userId);

        Doctor doctor = new Doctor();
        doctor.setId(doctorId);

        return new History(id, user, doctor, description, diagnosis, treatment, date);
    }
}
