package rha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Cura;
import rha.model.Paciente;

@RepositoryRestResource(collectionResourceRel = "curas", path = "curas")
public interface CuraRepository extends JpaRepository<Cura, Long> {

	@Query("SELECT c FROM Cura c "
			+ "WHERE c.valorada = :valorada "
			+ "AND c.proceso.paciente = :paciente "
			+ "ORDER BY c.id DESC")
	List<Cura> getCurasPacienteIdAndNotValoradas(@Param("paciente") Paciente paciente, 
			@Param("valorada") Boolean valorada);

}
