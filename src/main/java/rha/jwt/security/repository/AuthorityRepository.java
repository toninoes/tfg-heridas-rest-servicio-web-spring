package rha.jwt.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rha.jwt.model.security.Authority;
import rha.jwt.model.security.AuthorityName;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(AuthorityName name);
}
