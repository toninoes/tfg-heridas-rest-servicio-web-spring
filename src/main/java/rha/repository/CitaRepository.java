package rha.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Cita;
import rha.model.Paciente;
import rha.model.Sala;

@RepositoryRestResource(collectionResourceRel = "citas", path = "citas")
public interface CitaRepository extends JpaRepository<Cita, Long>{

	List<Cita> findTop10ByOrderByIdDesc();

	Long countBySalaAndFecha(@Param("sala") Sala sala, @Param("fecha") Date fecha);
	
	@Query("SELECT MAX(c.orden) FROM Cita c "
			+ "WHERE c.sala =:sala AND c.fecha =:fecha")
	Long findMaxOrden(@Param("sala") Sala sala, @Param("fecha") Date fecha);

	@Query("SELECT COUNT(*) FROM Cita c "
			+ "WHERE c.sala =:sala AND c.paciente =:paciente AND c.fecha >= :fecha")
	Long citasProximasPacienteEnEsaSala(@Param("sala") Sala sala, @Param("fecha") Date date, 
			@Param("paciente") Paciente paciente);
	
	@Query("SELECT COUNT(*) FROM Cita c "
			+ "WHERE c.sala =:sala AND c.fecha =:fecha AND c.orden < :orden")
	Long countCuantosHayDelante(@Param("sala") Sala sala, @Param("fecha") Date fecha, 
			@Param("orden") Long orden);
}
