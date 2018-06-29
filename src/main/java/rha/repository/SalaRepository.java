package rha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Sala;

@RepositoryRestResource(collectionResourceRel = "salas", path = "salas")
public interface SalaRepository extends JpaRepository<Sala, Long>{

}
