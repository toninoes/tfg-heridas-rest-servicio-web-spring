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
	
	List<Valoracion> findTop10ByOrderByIdDesc();
	
	@Query("SELECT v FROM Valoracion v "
			+ "WHERE v.observaciones LIKE %:texto% "
			+ "OR v.sanitario.firstname LIKE %:texto% "
			+ "OR v.sanitario.lastname LIKE %:texto% "
			+ "OR v.sanitario.colegiado LIKE %:texto% "
			+ "OR v.sanitario.dni LIKE %:texto%")
	List<Valoracion> findByFiltroContainingIgnoreCase(@Param("texto") String texto);
	
	
	// A partir de aquí con <ValoracionesResults>
	
	@Query("SELECT new rha.model.ValoracionesResults"
			+ "(AVG(v.nota) as notaMedia, v.sanitario as sanitario, COUNT(v.nota) as totalNotas) "
			+ "FROM Valoracion v "
			+ "GROUP BY v.sanitario "
			+ "ORDER BY AVG(v.nota) DESC")
	List<ValoracionesResults> findAvgNotaBySanitario();
	
	@Query("SELECT new rha.model.ValoracionesResults"
			+ "(AVG(v.nota) as notaMedia, v.sanitario as sanitario, COUNT(v.nota) as totalNotas) "
			+ "FROM Valoracion v "
			+ "WHERE v.sanitario.firstname LIKE %:texto% "
			+ "OR v.sanitario.lastname LIKE %:texto% "
			+ "OR v.sanitario.colegiado LIKE %:texto% "
			+ "OR v.sanitario.dni LIKE %:texto% "
			+ "GROUP BY v.sanitario "
			+ "ORDER BY AVG(v.nota) DESC")
	List<ValoracionesResults> findAvgByFiltroContainingIgnoreCase(@Param("texto") String texto);
	
}
