package millaku.altin.eshendetsia.repository;

import millaku.altin.eshendetsia.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
