package millaku.altin.eshendetsia.repository;

import millaku.altin.eshendetsia.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByDoctorId(Long doctorId);
    List<Feedback> findByUserId(Long userId);
}
