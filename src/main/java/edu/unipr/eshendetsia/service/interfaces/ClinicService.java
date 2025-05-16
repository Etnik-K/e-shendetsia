package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Clinic;

import java.util.List;

public interface ClinicService {
    List<Clinic> getAllClinics(String jwt);
    Clinic getClinicById(Long id, String jwt);
    void saveClinic(Clinic clinic, String jwt);
    void updateClinic(Long id, Clinic updateClinic, String jwt);
    void deleteClinic(Long id, String jwt);
}
