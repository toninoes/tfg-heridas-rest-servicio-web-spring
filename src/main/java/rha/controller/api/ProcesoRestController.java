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

import rha.model.Cura;
import rha.model.Proceso;
import rha.service.ProcesoService;

@RestController
@RequestMapping("/api/procesos")
public class ProcesoRestController {
	
	@Autowired
	private ProcesoService procesoService;
	
	@GetMapping
	public List<Proceso> findAll() {
		return procesoService.findAll();
	}
	
	@GetMapping("/{id}")
	public Proceso findById(@PathVariable long id) {
		return procesoService.findById(id);
	}
	
	@GetMapping("/{id}/curas")
	public List<Cura> findByProceso(@PathVariable long id) {
		return procesoService.findByProceso(id);
	}
	
	
	@GetMapping("/paciente/{id}")
	public List<Proceso> findAllByPacienteId(@PathVariable long id) {			
		return procesoService.findAllByPacienteId(id);
	}
	
	@PostMapping
	public ResponseEntity<Proceso> create(@Valid @RequestBody Proceso p) {
		return procesoService.create(p);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Proceso> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Proceso p) {
		return procesoService.update(id, p);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		return procesoService.delete(id);
	}

}
