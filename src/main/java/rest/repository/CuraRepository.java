package rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rest.model.Cura;

@RepositoryRestResource(collectionResourceRel = "curas", path = "curas")
public interface CuraRepository extends JpaRepository<Cura, Long> {

	@Query("SELECT c.foto FROM Cura c WHERE c.id = :id")
	Optional<String> fotoByCuraId(@Param("id") Long id);

	Optional<String> findByFoto(@Param("foto") String foto);
}
