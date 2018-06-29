package rha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import rha.model.Procedimiento;

@RepositoryRestResource(collectionResourceRel = "procedimientos", path = "procedimientos")
public interface ProcedimientoRepository extends JpaRepository<Procedimiento, Long> {

}
