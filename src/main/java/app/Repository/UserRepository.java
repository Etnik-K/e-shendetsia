package app.Repository;

import app.Model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u.salt FROM User u WHERE u.name = :username")
    public  String getSaltByUsername(@Param("username") String username);

    @Query("SELECT u.password FROM User u WHERE u.name = :username")
    public  String getHashByUsername(@Param("username") String username);
}

