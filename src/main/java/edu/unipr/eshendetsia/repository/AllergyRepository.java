package edu.unipr.eshendetsia.repository;

import edu.unipr.eshendetsia.model.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    List<Allergy> findByUserId(Long userId);
}
