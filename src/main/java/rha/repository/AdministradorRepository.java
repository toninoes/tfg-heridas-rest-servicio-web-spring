package rha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Administrador;


@RepositoryRestResource(collectionResourceRel = "administradores", path = "administradores")
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

	Optional<Administrador> findByDni(@Param("dni") String dni);	
}
