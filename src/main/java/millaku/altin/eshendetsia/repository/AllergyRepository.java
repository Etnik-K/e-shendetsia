package millaku.altin.eshendetsia.repository;

import millaku.altin.eshendetsia.model.entity.Allergy;
import millaku.altin.eshendetsia.model.entity.UserAllergy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    List<Allergy> findByUsers(Set<UserAllergy> users);
//    List<Allergy> findByUsers(Long userId);
}
