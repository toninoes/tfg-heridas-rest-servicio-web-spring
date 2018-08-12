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

import rha.model.Cita;
import rha.service.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaRestController {

	@Autowired
	private CitaService citaservice;
	
	@GetMapping
	public List<Cita> findAll() {
		return citaservice.findAll();
	}
	
	@GetMapping("/realizadas/{pacienteId}")
	public List<Cita> findRealizadasByPacienteId(@PathVariable long pacienteId) {
		return citaservice.findRealizadasByPacienteId(pacienteId);
	}
	
	@PostMapping("/agenda/{sanitarioId}")
	public List<Cita> findAgendaBySanitarioId(@PathVariable long sanitarioId, @RequestBody Cita c) {
		return citaservice.findAgendaBySanitarioId(sanitarioId, c);
	}
	
	@PostMapping("/posibles")
	public List<Cita> findPosiblesByPacienteAndSalaAndFecha(@RequestBody Cita c) {
		return citaservice.findPosiblesByPacienteAndSalaAndFecha(c);
	}
	
	@GetMapping("/{id}")
	public Cita findById(@PathVariable long id) {
		return citaservice.findById(id);
	}
	
	@GetMapping("/delante/{citaId}")
	public Long pacientesDelanteMia(@PathVariable long citaId) {
		return citaservice.pacientesDelanteMia(citaId);
	}
	
	@GetMapping("recientes")
	public List<Cita> findRecientes() {
		return citaservice.findRecientes();
	}
	
	@PostMapping
	public ResponseEntity<Cita> create(@Valid @RequestBody Cita c) {
		return citaservice.create(c);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Cita> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Cita c) {
		return citaservice.update(id, c);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return citaservice.delete(id);
	}
	
}
