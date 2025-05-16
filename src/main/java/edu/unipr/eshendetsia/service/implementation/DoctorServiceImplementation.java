package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.repository.DoctorRepository;
import edu.unipr.eshendetsia.service.interfaces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImplementation implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImplementation(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

}