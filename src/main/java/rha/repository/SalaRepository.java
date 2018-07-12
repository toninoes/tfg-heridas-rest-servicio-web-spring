package rha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Sala;

@RepositoryRestResource(collectionResourceRel = "salas", path = "salas")
public interface SalaRepository extends JpaRepository<Sala, Long>{

	@Query("SELECT s FROM Sala s "
			+ "WHERE s.nombre LIKE %:texto% "
			+ "OR s.centro.nombre LIKE %:texto% "
			+ "OR s.centro.telefono LIKE %:texto%")
	List<Sala> findByFiltroContainingIgnoreCase(@Param("texto") String texto);

}
