package millaku.altin.eshendetsia.repository;

import millaku.altin.eshendetsia.model.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestResultRepository  extends JpaRepository<TestResult, Long> {
    List<TestResult> findByUserId(Long userId);
    List<TestResult> findByDoctorId(Long doctorId);
}
