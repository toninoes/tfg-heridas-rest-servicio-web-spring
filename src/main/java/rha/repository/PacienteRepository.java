package rha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Paciente;


@RepositoryRestResource(collectionResourceRel = "pacientes", path = "pacientes")
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	List<Paciente> findByFirstname(@Param("firstname") String firstname);
	
	Optional<Paciente> findByDni(@Param("dni") String dni);

	@Query("SELECT MAX(p.historia) FROM Paciente p")
	Long findMaxHistoria();
	
	@Query("SELECT p FROM Paciente p "
			+ "WHERE p.dni LIKE %:texto% "
			+ "OR p.firstname LIKE %:texto% "
			+ "OR p.lastname LIKE %:texto% "
			+ "OR p.historia LIKE %:texto%")
	List<Paciente> findByFiltroContainingIgnoreCase(@Param("texto") String texto);
	
	List<Paciente> findTop10ByOrderByIdDesc();
}
