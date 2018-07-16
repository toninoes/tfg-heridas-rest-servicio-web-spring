package rha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Grupodiagnostico;

@RepositoryRestResource(collectionResourceRel = "gruposdiagnosticos", path = "gruposdiagnosticos")
public interface GrupodiagnosticoRepository extends JpaRepository<Grupodiagnostico, Long> {

	Optional<Grupodiagnostico> findByNombre(String nombre);

	@Query("SELECT gd FROM Grupodiagnostico gd "
			+ "WHERE gd.nombre LIKE %:texto%")
	List<Grupodiagnostico> findByFiltroContainingIgnoreCase(@Param("texto") String texto);

}
