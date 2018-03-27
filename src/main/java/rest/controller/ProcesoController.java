package rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.exception.ErrorInternoServidorException;
import rest.exception.RecursoNoEncontradoException;
import rest.model.Paciente;
import rest.model.Proceso;
import rest.repository.PacienteRepository;
import rest.repository.ProcesoRepository;

@RestController
@RequestMapping("/custom/procesos")
public class ProcesoController {
	
	
	private final ProcesoRepository procesoRepository;
	private final PacienteRepository pacienteRepository;
	
	@Autowired
	public ProcesoController(ProcesoRepository procesoRepository, PacienteRepository pacienteRepository) { 
		this.procesoRepository = procesoRepository;
		this.pacienteRepository = pacienteRepository;
	}
	
	@GetMapping
	public List<Proceso> findAll() {
		return procesoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Proceso findById(@PathVariable long id) {
		return procesoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Proceso", "id", id));
	}
	
	@GetMapping("/paciente/{id}")
	public List<Proceso> findAllByPacienteId(@PathVariable long id) {
		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));
				
		return procesoRepository.findByPaciente(paciente);
	}
	
	
	@PostMapping
	public ResponseEntity<Proceso> create(@Valid @RequestBody Proceso p) {
		pacienteRepository.findById(p.getPaciente().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", p.getPaciente().getId()));
		
		Proceso proceso = new Proceso();
		
		try {
			proceso = procesoRepository.save(p);
		} catch (ErrorInternoServidorException e) {
			throw new ErrorInternoServidorException("Guardar", "Proceso", p.getId(), e.getMessage());
		}
		
		return new ResponseEntity<Proceso>(proceso, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Proceso> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Proceso p) {

		Proceso proceso = procesoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));

		try {
			proceso.setAnamnesis(p.getAnamnesis());
			proceso.setDiagnostico(p.getDiagnostico());
			proceso.setTipo(p.getTipo());
			procesoRepository.save(proceso);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("Actualizar", "Proceso", id, e.getMessage());
		}
		
		return new ResponseEntity<Proceso>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    Proceso proceso = procesoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Proceso", "id", id));

	    try {
	    	procesoRepository.delete(proceso);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("Borrar", "Proceso", id, e.getMessage());
		}

	    return ResponseEntity.ok().build();
	}

}
