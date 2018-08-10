package rha.config.salas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "salasconfigs", path = "salasconfigs")
public interface SalaConfigRepository extends JpaRepository<SalaConfig, Long>{


}
