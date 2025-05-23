package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Appointment;
import edu.unipr.eshendetsia.repository.AppointmentRepository;
import edu.unipr.eshendetsia.service.interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementimi i sherbimit per menaxhimin e termineve
 */
@Service
public class AppointmentServiceImplementation implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    /**
     * Konstruktori per inicializimin e sherbimit te termineve
     *
     * @param appointmentRepository repository per qasje ne te dhena te termineve
     */
    @Autowired
    public AppointmentServiceImplementation(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    /**
     * Ruan terminin ne sistem
     *
     * @param appointment termini per tu ruajtur
     * @return termini i ruajtur
     */
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    /**
     * Anulon terminin me ID te caktuar
     *
     * @param id identifikuesi i terminit per tu anuluar
     */
    public void cancel(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null) {
            appointment.setStatus("CANCELED");
            appointmentRepository.save(appointment);
        }
    }
}
