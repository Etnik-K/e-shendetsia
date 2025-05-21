package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.repository.EmergencyContactRepository;
import edu.unipr.eshendetsia.service.interfaces.EmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unipr.eshendetsia.model.EmergencyContact;

import java.util.List;

/**
 * Implementimi i sherbimit per kontaktet emergjente
 * Menaxhon operacionet CRUD per kontaktet emergjente
 */
@Service
public class EmergencyContactServiceImplementation implements EmergencyContactService{

    private final EmergencyContactRepository repository;

    /**
     * Konstruktori i klases
     *
     * @param repository repository per kontaktet emergjente
     */
    @Autowired
    public EmergencyContactServiceImplementation(EmergencyContactRepository repository) {
        this.repository = repository;
    }

    /**
     * Ruan nje kontakt emergjent ne sistem
     *
     * @param contact kontakti emergjent per tu ruajtur
     * @return kontakti i ruajtur
     */
    public EmergencyContact save(EmergencyContact contact) {
        return repository.save(contact);
    }

    /**
     * Merr listen e kontakteve emergjente per nje perdorues
     *
     * @param userId ID e perdoruesit
     * @return lista e kontakteve emergjente
     */
    public List<EmergencyContact> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    /**
     * Fshin nje kontakt emergjent nga sistemi
     *
     * @param id ID e kontaktit per tu fshire
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
