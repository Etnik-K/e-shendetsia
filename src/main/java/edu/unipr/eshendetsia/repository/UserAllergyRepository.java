package edu.unipr.eshendetsia.repository;
import edu.unipr.eshendetsia.model.entity.User;
import edu.unipr.eshendetsia.model.entity.UserAllergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAllergyRepository extends JpaRepository<UserAllergy, Long> {
    Optional<List<UserAllergy>> findByUser(User user);
}
