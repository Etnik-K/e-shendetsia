package edu.unipr.eshendetsia.repository;

import edu.unipr.eshendetsia.model.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<Allergy, Long> { }