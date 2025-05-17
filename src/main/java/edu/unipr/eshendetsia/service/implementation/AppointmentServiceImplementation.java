package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Appointment;
import edu.unipr.eshendetsia.repository.AppointmentRepository;
import edu.unipr.eshendetsia.service.interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImplementation(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void cancel(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null) {
            appointment.setStatus("CANCELED");
            appointmentRepository.save(appointment);
        }
    }
}
