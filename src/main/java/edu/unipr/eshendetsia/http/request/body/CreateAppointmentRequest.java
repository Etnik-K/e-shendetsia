package edu.unipr.eshendetsia.http.request.body;

import edu.unipr.eshendetsia.model.entity.Appointment;

import java.time.LocalDateTime;

public record CreateAppointmentRequest (
        Long id, Long userId, Long doctorId, LocalDateTime appointmentTime, String reason, String status
) {
    public Appointment toAppointment() {
        return new Appointment(id, userId, doctorId, appointmentTime, reason, status);
    }
}
