package repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import modelos.Person;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findByLastName(@Param("name") String name);

}
