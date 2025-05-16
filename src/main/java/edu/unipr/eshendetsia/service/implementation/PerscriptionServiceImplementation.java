package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.model.entity.Perscription;
import edu.unipr.eshendetsia.repository.PerscriptionRepository;
import edu.unipr.eshendetsia.service.interfaces.PersctriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerscriptionServiceImplementation implements PersctriptionService {
    
    private PerscriptionRepository persciptionRepository;
    
    @Autowired
    public PerscriptionServiceImplementation(PerscriptionRepository persciptionRepository) {
        this.persciptionRepository = persciptionRepository;
    }
 
    public List<Perscription> findByUserId(Long userId) {
        return persciptionRepository.findByUserId(userId);
    }
    public List<Perscription> findByDoctorId(Long doctorId) {
        return persciptionRepository.findByDoctorId(doctorId);
    }

    public Perscription save(Perscription prescription) {
        return persciptionRepository.save(prescription);
    }
}
