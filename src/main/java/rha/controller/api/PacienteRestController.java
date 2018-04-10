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

import rha.model.Paciente;
import rha.service.PacienteService;


@RestController
@RequestMapping("/api/pacientes")
public class PacienteRestController {

	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping
	public List<Paciente> findAll() {
		return pacienteService.findAll();
	}
	
	@GetMapping("/{id}")
	public Paciente findById(@PathVariable long id) {
		return pacienteService.findById(id);
	}
	
	@GetMapping("/dni/{dni}")
	public Paciente findByDni(@PathVariable String dni) {
		return pacienteService.findByDni(dni);
	}
	
	@PostMapping
	public ResponseEntity<Paciente> create(@Valid @RequestBody Paciente p) {
		return pacienteService.create(p);
    }
	
	@PutMapping("/{id}")	
	public ResponseEntity<Paciente> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Paciente p) {
		return pacienteService.update(id, p);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		return pacienteService.delete(id);
	}

}
