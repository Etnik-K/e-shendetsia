package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.Appointment;

public interface AppointmentService {
    Appointment save(Appointment appointment);
    void cancel(Long id);
}
