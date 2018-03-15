package repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import modelos.Paciente;

@RepositoryRestResource(collectionResourceRel = "paciente", path = "paciente")
public interface PacienteRepository extends CrudRepository<Paciente, Long> {

	List<Paciente> findByNombre(@Param("nombre") String nombre);

}
