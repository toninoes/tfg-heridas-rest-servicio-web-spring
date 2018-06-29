package rha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Centro;

@RepositoryRestResource(collectionResourceRel = "centros", path = "centros")
public interface CentroRepository extends JpaRepository<Centro, Long> {

}
