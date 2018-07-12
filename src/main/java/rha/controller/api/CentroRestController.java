package rha.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/filtro")
	public List<Centro> findByFiltro(@RequestParam(value = "filtro", required = false) String filtro) {
		return centroService.findByFiltro(filtro);
	}
	
	@GetMapping("/{id}")
	public Centro findById(@PathVariable long id) {
		return centroService.findById(id);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Centro> create(@Valid @RequestBody Centro c) {
		return centroService.create(c);
	}
	
	@PutMapping("/{id}")	
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Centro> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Centro c) {
		return centroService.update(id, c);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return centroService.delete(id);
	}

}
