package rha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Sanitario;


@RepositoryRestResource(collectionResourceRel = "sanitarios", path = "sanitarios")
public interface SanitarioRepository extends JpaRepository<Sanitario, Long> {
	Optional<Sanitario> findByColegiado(@Param("colegiado") Long colegiado);
	Optional<Sanitario> findByDni(@Param("dni") String dni);	
}
