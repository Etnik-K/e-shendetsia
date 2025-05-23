package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.Appointment;
import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.model.enums.AppointmentStatus;

import java.time.LocalDateTime;

public record CreateAppointmentRequest (
        Long id, Long userId, Long doctorId, LocalDateTime appointmentTime, String reason, AppointmentStatus status
) {
    public Appointment toAppointment() {
        User user = new User();
        Doctor doctor = new Doctor();

        user.setId(userId);
        doctor.setId(doctorId);

        return new Appointment(id, user, doctor, appointmentTime, reason, status);
    }
}
