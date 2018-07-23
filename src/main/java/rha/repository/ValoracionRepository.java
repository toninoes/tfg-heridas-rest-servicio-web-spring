package rha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Sanitario;
import rha.model.Valoracion;
import rha.model.ValoracionesResults;

@RepositoryRestResource(collectionResourceRel = "valoraciones", path = "valoraciones")
public interface ValoracionRepository extends JpaRepository<Valoracion, Long>{

	List<Valoracion> findBySanitarioOrderByIdDesc(@Param("sanitario") Sanitario sanitario);

	List<Valoracion> findAllByOrderByIdDesc();
	
	@Query("SELECT new rha.model.ValoracionesResults"
			+ "(AVG(v.nota) as notaMedia, v.sanitario as sanitario) "
			+ "FROM Valoracion v "
			+ "GROUP BY v.sanitario "
			+ "ORDER BY AVG(v.nota) DESC")
	List<ValoracionesResults> findAvgNotaBySanitario();


}
