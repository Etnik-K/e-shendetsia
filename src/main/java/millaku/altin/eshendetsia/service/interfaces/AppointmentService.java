package millaku.altin.eshendetsia.service.interfaces;

import millaku.altin.eshendetsia.model.entity.Appointment;

public interface AppointmentService {
    Appointment save(Appointment appointment);
    void cancel(Long id);
}
