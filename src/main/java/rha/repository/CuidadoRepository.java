package rha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Cuidado;

@RepositoryRestResource(collectionResourceRel = "cuidados", path = "cuidados")
public interface CuidadoRepository extends JpaRepository<Cuidado, Long>{

}
