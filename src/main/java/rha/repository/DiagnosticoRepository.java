package rha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Diagnostico;

@RepositoryRestResource(collectionResourceRel = "diagnosticos", path = "diagnosticos")
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {

}
