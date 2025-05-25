package millaku.altin.eshendetsia.service.interfaces;

import millaku.altin.eshendetsia.model.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Optional<Doctor> getDoctorById(Long id);
}
