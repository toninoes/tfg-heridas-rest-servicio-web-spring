package rha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Imagen;

@RepositoryRestResource(collectionResourceRel = "imagenes", path = "imagenes")
public interface ImagenRepository extends JpaRepository<Imagen, Long> {

}
