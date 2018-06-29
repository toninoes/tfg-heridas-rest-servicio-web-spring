package rha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Grupodiagnostico;

@RepositoryRestResource(collectionResourceRel = "gruposdiagnosticos", path = "gruposdiagnosticos")
public interface GrupodiagnosticoRepository extends JpaRepository<Grupodiagnostico, Long> {

}
