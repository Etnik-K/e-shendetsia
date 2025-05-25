package millaku.altin.eshendetsia.service.implementation;

import millaku.altin.eshendetsia.model.entity.Doctor;
import millaku.altin.eshendetsia.repository.DoctorRepository;
import millaku.altin.eshendetsia.service.interfaces.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementimi i sherbimit te doktorit qe perfshin
 * operacionet themelore per menaxhimin e doktoreve
 */
@Service
public class DoctorServiceImplementation implements DoctorService {

    private final DoctorRepository doctorRepository;

    /**
     * Konstruktori i klases
     *
     * @param doctorRepository repository per qasje ne te dhenat e doktoreve
     */
    @Autowired
    public DoctorServiceImplementation(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /**
     * Merr listen e te gjithe doktoreve
     *
     * @return lista e doktoreve ne sistem
     */
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    /**
     * Gjen doktorin sipas identifikuesit
     *
     * @param id identifikuesi i doktorit
     * @return doktori i gjetur ose Optional bosh
     */
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

}