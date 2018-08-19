package rha.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rha.model.Cuidado;
import rha.service.CuidadoService;

@RestController
@RequestMapping("/api/cuidados")
public class CuidadoRestController {
	
	@Autowired
	private CuidadoService cuidadoService;
	
	@GetMapping
	public List<Cuidado> findAll() {
		return cuidadoService.findAll();
	}
	
	@GetMapping("/{id}")
	public Cuidado findById(@PathVariable long id) {
		return cuidadoService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Cuidado> create(@Valid @RequestBody Cuidado c) {
		return cuidadoService.create(c);
    }
	
	@PutMapping("/{id}")	
	public ResponseEntity<Cuidado> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Cuidado c) {
		return cuidadoService.update(id, c);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return cuidadoService.delete(id);
	}
	

}
