package rha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Paciente;
import rha.model.Proceso;


@RepositoryRestResource(collectionResourceRel = "procesos", path = "procesos")
public interface ProcesoRepository extends JpaRepository<Proceso, Long> {
	
	List<Proceso> findByPaciente(@Param("paciente") Paciente paciente);
}
