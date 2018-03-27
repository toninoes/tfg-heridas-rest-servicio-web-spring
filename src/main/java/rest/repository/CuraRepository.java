package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rest.model.Cura;

@RepositoryRestResource(collectionResourceRel = "curas", path = "curas")
public interface CuraRepository extends JpaRepository<Cura, Long> {

}
