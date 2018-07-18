package rha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Procedimiento;

@RepositoryRestResource(collectionResourceRel = "procedimientos", path = "procedimientos")
public interface ProcedimientoRepository extends JpaRepository<Procedimiento, Long> {
	
	@Query("SELECT p FROM Procedimiento p "
			+ "WHERE p.nombre LIKE %:texto% "
			+ "OR p.codigo LIKE %:texto%")
	List<Procedimiento> findByFiltroContainingIgnoreCase(@Param("texto") String texto);

}
