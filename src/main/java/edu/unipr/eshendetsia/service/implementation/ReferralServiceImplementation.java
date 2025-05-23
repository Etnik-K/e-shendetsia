package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Referral;
import edu.unipr.eshendetsia.repository.ReferralRepository;
import edu.unipr.eshendetsia.service.interfaces.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementimi i sherbimit per referime mjekesore
 */
@Service
public class ReferralServiceImplementation implements ReferralService {
    private final ReferralRepository repository;

    /**
     * Konstruktori i klases
     *
     * @param repository repository per referime
     */
    @Autowired
    public ReferralServiceImplementation(ReferralRepository repository) {
        this.repository = repository;
    }

    /**
     * Ruan nje referim te ri
     *
     * @param referral referimi per tu ruajtur
     * @return referimin e ruajtur
     */
    public Referral save(Referral referral) {
        return repository.save(referral);
    }

    /**
     * Merr referimet e nje pacienti
     *
     * @param patientId ID e pacientit
     * @return lista e referimeve
     */
    public List<Referral> getByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    /**
     * Merr referimet per nje doktor
     *
     * @param doctorId ID e doktorit
     * @return lista e referimeve
     */
    public List<Referral> getByReceivingDoctor(Long doctorId) {
        return repository.findByToDoctorId(doctorId);
    }

    /**
     * Fshin nje referim
     *
     * @param id ID e referimit per tu fshire
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
