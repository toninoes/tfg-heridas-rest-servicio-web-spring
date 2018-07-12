package rha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Centro;

@RepositoryRestResource(collectionResourceRel = "centros", path = "centros")
public interface CentroRepository extends JpaRepository<Centro, Long> {

	Optional<Centro> findByNombre(String nombre);
	
	@Query("SELECT c FROM Centro c "
			+ "WHERE c.nombre LIKE %:texto% "
			+ "OR c.direccion LIKE %:texto% "
			+ "OR c.telefono LIKE %:texto%")
	List<Centro> findByFiltroContainingIgnoreCase(@Param("texto") String texto);

}
