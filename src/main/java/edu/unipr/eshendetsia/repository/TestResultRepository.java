package edu.unipr.eshendetsia.repository;

import edu.unipr.eshendetsia.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestResultRepository  extends JpaRepository<TestResult, Long> {
    List<TestResult> findByUserId(Long userId);
    List<TestResult> findByDoctorId(Long doctorId);
}
