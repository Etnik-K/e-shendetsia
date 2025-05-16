package edu.unipr.eshendetsia.repository;

import edu.unipr.eshendetsia.model.entity.Role;
import edu.unipr.eshendetsia.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Role getRoleById(long id);

    Optional<Set<Role>> findRolesById(long userId);
}