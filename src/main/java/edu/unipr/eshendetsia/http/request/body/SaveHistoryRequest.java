package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.model.entity.History;
import edu.unipr.eshendetsia.model.entity.User;

import java.time.LocalDateTime;

public record SaveHistoryRequest(
    Long id, Long userId, Long doctorId, String description, String diagnosis, String treatment, LocalDateTime date
) {
    public History toHistory() {

        User user = new User();
        Doctor doctor = new Doctor();

        user.setId(userId);
        doctor.setId(doctorId);

        return new History(id, user, doctor, description, diagnosis, treatment, date);
    }
}
