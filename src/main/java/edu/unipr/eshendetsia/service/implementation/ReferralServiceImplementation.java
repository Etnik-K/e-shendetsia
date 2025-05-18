package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Referral;
import edu.unipr.eshendetsia.repository.ReferralRepository;
import edu.unipr.eshendetsia.service.interfaces.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferralServiceImplementation implements ReferralService {
    private final ReferralRepository repository;

    @Autowired
    public ReferralServiceImplementation(ReferralRepository repository) {
        this.repository = repository;
    }

    public Referral save(Referral referral) {
        return repository.save(referral);
    }

    public List<Referral> getByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    public List<Referral> getByReceivingDoctor(Long doctorId) {
        return repository.findByToDoctorId(doctorId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
