package app.Service;

import app.Model.Clinic;
import app.Repository.ClinicRepository;


import java.util.List;
import java.util.Optional;

public class ClinicService {


    private ClinicRepository clinicRepository;
    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Optional<Clinic> getClinicById(Long id) {
        return clinicRepository.findById(id);
    }


}
