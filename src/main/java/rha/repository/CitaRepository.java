package rha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Cita;

@RepositoryRestResource(collectionResourceRel = "citas", path = "citas")
public interface CitaRepository extends JpaRepository<Cita, Long>{

}
