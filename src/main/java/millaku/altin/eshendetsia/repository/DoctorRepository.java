package millaku.altin.eshendetsia.repository;

import millaku.altin.eshendetsia.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> { }
