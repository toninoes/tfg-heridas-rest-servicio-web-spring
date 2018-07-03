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

import rha.model.Sanitario;
import rha.service.SanitarioService;

@RestController
@RequestMapping("/api/sanitarios")
public class SanitarioRestController {
	
	@Autowired
	private SanitarioService sanitarioService;
	
	@GetMapping
	public List<Sanitario> findAll() {
		return sanitarioService.findAll();
	}
	
	@GetMapping("/{id}")
	public Sanitario findById(@PathVariable long id) {
		return sanitarioService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Sanitario> create(@Valid @RequestBody Sanitario s) {
		return sanitarioService.create(s);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Sanitario> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Sanitario s) {
		return sanitarioService.update(id, s);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return sanitarioService.delete(id);
	}

}
