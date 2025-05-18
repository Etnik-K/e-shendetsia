package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.repository.EmergencyContactRepository;
import edu.unipr.eshendetsia.service.interfaces.EmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unipr.eshendetsia.model.entity.EmergencyContact;

import java.util.List;

@Service
public class EmergencyContactServiceImplementation {

    private final EmergencyContactRepository repository;

    @Autowired
    public EmergencyContactServiceImplementation(EmergencyContactRepository repository) {
        this.repository = repository;
    }

    public EmergencyContact save(EmergencyContact contact) {
        return repository.save(contact);
    }

    public List<EmergencyContact> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
