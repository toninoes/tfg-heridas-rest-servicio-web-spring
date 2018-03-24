package rest.proceso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rest.paciente.Paciente;


@RepositoryRestResource(collectionResourceRel = "procesos", path = "procesos")
public interface ProcesoRepository extends JpaRepository<Proceso, Long> {
	
	@Query("SELECT p FROM Proceso p WHERE p.paciente = :paciente")
	List<Proceso> findByPaciente(@Param("paciente") Paciente paciente);
}
