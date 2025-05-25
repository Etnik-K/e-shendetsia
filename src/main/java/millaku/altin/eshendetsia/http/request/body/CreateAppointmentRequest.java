package millaku.altin.eshendetsia.http.request.body;

import millaku.altin.eshendetsia.model.entity.Appointment;
import millaku.altin.eshendetsia.model.entity.Doctor;
import millaku.altin.eshendetsia.model.entity.User;
import millaku.altin.eshendetsia.model.enums.AppointmentStatus;

import java.time.LocalDateTime;

public record CreateAppointmentRequest (
        Long id, Long userId, Long doctorId, LocalDateTime appointmentTime, String reason, String status
) {
    public Appointment toAppointment() {
        User user = new User();
        user.setId(userId);

        Doctor doctor = new Doctor();
        doctor.setId(doctorId);

        return new Appointment(id, user, doctor, appointmentTime, reason, AppointmentStatus.SCHEDULED);
    }
}
