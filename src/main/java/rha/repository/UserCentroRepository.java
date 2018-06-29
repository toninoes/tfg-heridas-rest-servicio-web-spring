package rha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.UserCentro;

@RepositoryRestResource(collectionResourceRel = "userscentros", path = "userscentros")
public interface UserCentroRepository extends JpaRepository<UserCentro, Long>{

}
