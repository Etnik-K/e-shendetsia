package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.model.entity.Perscription;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PersctriptionService {

    List<Perscription> findByUserId(Long userId);
    List<Perscription> findByDoctorId(Long doctorId);
    Perscription save(Perscription prescription);
}
