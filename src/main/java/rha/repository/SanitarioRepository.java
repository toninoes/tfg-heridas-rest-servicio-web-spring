package rha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Sanitario;


@RepositoryRestResource(collectionResourceRel = "sanitarios", path = "sanitarios")
public interface SanitarioRepository extends JpaRepository<Sanitario, Long> {
	Optional<Sanitario> findByColegiado(@Param("colegiado") String colegiado);
	Optional<Sanitario> findByDni(@Param("dni") String dni);
	
	@Query("SELECT s FROM Sanitario s "
			+ "WHERE s.dni LIKE %:texto% "
			+ "OR s.firstname LIKE %:texto% "
			+ "OR s.lastname LIKE %:texto% "
			+ "OR s.colegiado LIKE %:texto%")
	List<Sanitario> findByFiltroContainingIgnoreCase(@Param("texto") String texto);
	
	List<Sanitario> findTop10ByOrderByIdDesc();
}
