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

import rha.model.Centro;
import rha.service.CentroService;

@RestController
@RequestMapping("/api/centros")
public class CentroRestController {
	
	@Autowired
	private CentroService centroService;
	
	@GetMapping
	public List<Centro> findAll() {
		return centroService.findAll();
	}
	
	@GetMapping("/{id}")
	public Centro findById(@PathVariable long id) {
		return centroService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Centro> create(@Valid @RequestBody Centro c) {
		return centroService.create(c);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Centro> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Centro c) {
		return centroService.update(id, c);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return centroService.delete(id);
	}

}
