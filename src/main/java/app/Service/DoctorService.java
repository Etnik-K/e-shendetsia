package app.Service;

import app.Model.Doctor;
import app.Repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

public class DoctorService {

    private DoctorRepository doctorRepository;
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }


}
