package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id, String authHeader);
}
