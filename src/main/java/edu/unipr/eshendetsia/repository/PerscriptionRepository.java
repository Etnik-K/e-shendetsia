package edu.unipr.eshendetsia.repository;

import edu.unipr.eshendetsia.model.entity.Perscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerscriptionRepository extends JpaRepository<Perscription, Long> {
    List<Perscription> findByUserId(Long userId);
    List<Perscription> findByDoctorId(Long userId);

}
