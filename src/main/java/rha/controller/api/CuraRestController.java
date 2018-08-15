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
import rha.service.CuraService;

@RestController
@RequestMapping("/api/curas")
public class CuraRestController {
	
	@Autowired
	private CuraService curaService;
	
	@GetMapping
	public List<Cura> findAll() {
		return curaService.findAll();
	}
	
	@GetMapping("/{id}")
	public Cura findById(@PathVariable long id) {
		return curaService.findById(id);
	}

	@PostMapping("/{sanitarioId}")
	public ResponseEntity<Cura> create(@PathVariable long sanitarioId, @RequestBody Cura c) {
		return curaService.create(sanitarioId, c);
    }

	@PutMapping("/{id}")	
	public ResponseEntity<Cura> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Cura c) {
		return curaService.update(id, c);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return curaService.delete(id);
	}
	
	@GetMapping("/novaloradas/bypaciente/{pacienteId}")
	public List<Cura> findByPacienteIdAndNotValoradas(@PathVariable long pacienteId) {
		return curaService.findByPacienteIdAndNotValoradas(pacienteId);
	}

	
}
