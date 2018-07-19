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

import rha.model.Administrador;
import rha.service.AdministradorService;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorRestController {
	
	@Autowired
	private AdministradorService administradorService;
	
	@GetMapping
	public List<Administrador> findAll() {
		return administradorService.findAll();
	}
	
	@GetMapping("/{id}")
	public Administrador findById(@PathVariable long id) {
		return administradorService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Administrador> create(@Valid @RequestBody Administrador a) {
		return administradorService.create(a);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Administrador> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Administrador a) {
		return administradorService.update(id, a);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return administradorService.delete(id);
	}

}
