package app.model.clinic;

import app.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository, UserRepository userRepository) {
        this.clinicRepository = clinicRepository;
        this.userRepository = userRepository;
    }

    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    public Optional<Clinic> getClinicById(Long id) {
        return clinicRepository.findById(id);
    }

    public Clinic saveClinic(Clinic clinic) {
        if (clinic.getDrejtori() != null && !userRepository.existsById(clinic.getDrejtori().getId())) {
            throw new IllegalArgumentException("Director does not exist");
        }
        return clinicRepository.save(clinic);
    }

    public Optional<Clinic> updateClinic(Long id, Clinic updatedClinic) {
        Optional<Clinic> existingClinic = clinicRepository.findById(id);
        if (existingClinic.isPresent()) {
            if (updatedClinic.getDrejtori() != null && !userRepository.existsById(updatedClinic.getDrejtori().getId())) {
                throw new IllegalArgumentException("Director does not exist");
            }
            Clinic clinic = existingClinic.get();
            clinic.setAddress(updatedClinic.getAddress());
            clinic.setEmail(updatedClinic.getEmail());
            clinic.setPhone(updatedClinic.getPhone());
            clinic.setWebsite(updatedClinic.getWebsite());
            clinic.setDrejtori(updatedClinic.getDrejtori());
            return Optional.of(clinicRepository.save(clinic));
        }
        return Optional.empty();
    }

    public boolean deleteClinic(Long id) {
        if (clinicRepository.existsById(id)) {
            clinicRepository.deleteById(id);
            return true;
        }
        return false;
    }
}