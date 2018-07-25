package rha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Centro;
import rha.model.UserCentro;

@RepositoryRestResource(collectionResourceRel = "userscentros", path = "userscentros")
public interface UserCentroRepository extends JpaRepository<UserCentro, Long>{

	@Query("SELECT uc.centro "
			+ "FROM UserCentro uc "
			+ "WHERE uc.user.id = :userId AND uc.fin IS NULL")
	Optional<Centro> getCentroActualByUser(@Param("userId") long userId);

}
