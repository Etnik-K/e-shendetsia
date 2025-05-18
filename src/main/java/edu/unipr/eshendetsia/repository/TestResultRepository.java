package edu.unipr.eshendetsia.repository;

import edu.unipr.eshendetsia.model.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestResultRepository  extends JpaRepository<TestResult, Long> {
    List<TestResult> findByUserId(Long userId);
    List<TestResult> findByDoctorId(Long doctorId);
}
