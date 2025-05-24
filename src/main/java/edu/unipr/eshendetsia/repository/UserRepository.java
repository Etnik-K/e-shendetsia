package edu.unipr.eshendetsia.repository;

import edu.unipr.eshendetsia.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
}