package rha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Diagnostico;
import rha.model.Grupodiagnostico;

@RepositoryRestResource(collectionResourceRel = "diagnosticos", path = "diagnosticos")
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
	
	@Query("SELECT d FROM Diagnostico d "
			+ "WHERE d.nombre LIKE %:texto% "
			+ "OR d.codigo LIKE %:texto%")
	List<Diagnostico> findByFiltroContainingIgnoreCase(@Param("texto") String texto);

	List<Diagnostico> findByGrupodiagnostico(Grupodiagnostico grupodiagnostico);

}
