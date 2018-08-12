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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rha.model.Sala;
import rha.service.SalaService;

@RestController
@RequestMapping("/api/salas")
public class SalaRestController {
	
	@Autowired
	private SalaService salaService;
	
	@GetMapping
	public List<Sala> findAll() {
		return salaService.findAll();
	}
	
	@GetMapping("/filtro")
	public List<Sala> findByFiltro(@RequestParam(value = "filtro", required = false) String filtro) {
		return salaService.findByFiltro(filtro);
	}
	
	@GetMapping("/recientes")
	public List<Sala> findRecientes() {
		return salaService.findRecientes();
	}
	
	@GetMapping("/user/{userId}")
	public List<Sala> findByUserId(@PathVariable long userId) {
		return salaService.findByUserId(userId);
	}	
	
	@GetMapping("/{id}")
	public Sala findById(@PathVariable long id) {
		return salaService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Sala> create(@Valid @RequestBody Sala s) {
		return salaService.create(s);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Sala> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Sala s) {
		return salaService.update(id, s);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return salaService.delete(id);
	}

}
