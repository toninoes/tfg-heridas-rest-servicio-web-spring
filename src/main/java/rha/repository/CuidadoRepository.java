package rha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Cuidado;
import rha.model.Diagnostico;

@RepositoryRestResource(collectionResourceRel = "cuidados", path = "cuidados")
public interface CuidadoRepository extends JpaRepository<Cuidado, Long> {

	List<Cuidado> findByDiagnostico(Diagnostico diagnostico);
	
	List<Cuidado> findByDiagnosticoOrderByIdDesc(Diagnostico diagnostico);

	Optional<Cuidado> findByNombre(String nombre);

}
