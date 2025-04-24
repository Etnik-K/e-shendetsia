package app.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u.salt FROM User u WHERE u.name = :username")
    public  String getSaltByUsername(@Param("username") String username);

    @Query("SELECT u.password FROM User u WHERE u.name = :username")
    public  String getHashByUsername(@Param("username") String username);
}

