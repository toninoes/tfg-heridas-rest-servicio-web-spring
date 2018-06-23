package rha.jwt.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import rha.jwt.model.security.User;


public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByFirstname(@Param("firstname") String firstname);
	List<User> findByLastname(@Param("lastname") String lastname);
	Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
