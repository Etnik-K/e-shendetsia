package edu.unipr.eshendetsia.repository;

import edu.unipr.eshendetsia.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> { }