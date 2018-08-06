package rha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Cura;
import rha.model.Imagen;

@RepositoryRestResource(collectionResourceRel = "imagenes", path = "imagenes")
public interface ImagenRepository extends JpaRepository<Imagen, Long> {

	List<Imagen> findByCura(Cura cura);

}
