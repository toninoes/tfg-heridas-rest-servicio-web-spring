package rha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Administrador;


@RepositoryRestResource(collectionResourceRel = "administradores", path = "administradores")
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

	Optional<Administrador> findByDni(@Param("dni") String dni);	
	
	@Query("SELECT a FROM Administrador a "
			+ "WHERE a.dni LIKE %:texto% "
			+ "OR a.firstname LIKE %:texto% "
			+ "OR a.lastname LIKE %:texto%")
	List<Administrador> findByFiltroContainingIgnoreCase(@Param("texto") String texto);
	
	List<Administrador> findTop10ByOrderByIdDesc();
}
