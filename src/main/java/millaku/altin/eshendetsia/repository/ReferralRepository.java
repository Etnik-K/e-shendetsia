package millaku.altin.eshendetsia.repository;

import millaku.altin.eshendetsia.model.entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferralRepository extends JpaRepository<Referral, Long> {
    List<Referral> findByPatientId(Long patientId);
    List<Referral> findByToDoctorId(Long doctorId);
}
