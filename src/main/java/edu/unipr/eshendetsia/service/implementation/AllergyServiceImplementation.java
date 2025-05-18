package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Allergy;
import edu.unipr.eshendetsia.repository.AllergyRepository;
import edu.unipr.eshendetsia.service.interfaces.AllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllergyServiceImplementation implements AllergyService {

    private final AllergyRepository allergyRepository;

    @Autowired
    public AllergyServiceImplementation(AllergyRepository allergyRepository) {
        this.allergyRepository = allergyRepository;
    }

    public Allergy save(Allergy allergy) {
        return allergyRepository.save(allergy);
    }

    public List<Allergy> getByUserId(Long userId) {
        return allergyRepository.findByUserId(userId);
    }

    public void delete(Long id) {
        allergyRepository.deleteById(id);
    }
}
