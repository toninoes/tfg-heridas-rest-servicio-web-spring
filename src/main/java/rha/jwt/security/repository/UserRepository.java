package rha.jwt.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rha.jwt.model.security.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
}
