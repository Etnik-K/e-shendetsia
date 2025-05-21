package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Optional<Doctor> getDoctorById(Long id);
}
