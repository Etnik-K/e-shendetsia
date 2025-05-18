package edu.unipr.eshendetsia.repository;

import edu.unipr.eshendetsia.model.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByUserId(Long userId);
}
