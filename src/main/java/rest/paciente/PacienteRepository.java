package rest.paciente;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	List<Paciente> findByNombre(@Param("nombre") String nombre);

}
