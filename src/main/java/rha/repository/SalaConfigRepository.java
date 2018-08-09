package rha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.SalaConfig;

@RepositoryRestResource(collectionResourceRel = "salasconfigs", path = "salasconfigs")
public interface SalaConfigRepository extends JpaRepository<SalaConfig, Long>{


}
