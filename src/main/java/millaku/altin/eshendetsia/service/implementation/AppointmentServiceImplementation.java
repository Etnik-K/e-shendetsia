package millaku.altin.eshendetsia.service.implementation;

import millaku.altin.eshendetsia.model.entity.Appointment;
import millaku.altin.eshendetsia.model.enums.AppointmentStatus;
import millaku.altin.eshendetsia.repository.AppointmentRepository;
import millaku.altin.eshendetsia.service.interfaces.AppointmentService;
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
            appointment.setStatus(AppointmentStatus.CANCELLED);
            appointmentRepository.save(appointment);
        }
    }
}
