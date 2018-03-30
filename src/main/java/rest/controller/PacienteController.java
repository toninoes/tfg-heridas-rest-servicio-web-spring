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

import rest.exception.CampoUnicoException;
import rest.exception.ErrorInternoServidorException;
import rest.exception.RecursoNoEncontradoException;
import rest.model.Paciente;
import rest.repository.PacienteRepository;


@RestController
@RequestMapping("/custom/pacientes")
public class PacienteController {

	private final PacienteRepository pacienteRepository;
	
	@Autowired
	public PacienteController(PacienteRepository pacienteRepository) { 
		this.pacienteRepository = pacienteRepository;
	}
	
	@GetMapping
	public List<Paciente> findAll() {
		return pacienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Paciente findById(@PathVariable long id) {
		return pacienteRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));
	}
	
	@GetMapping("/dni/{dni}")
	public Paciente findByDni(@PathVariable String dni) {
		return pacienteRepository.findByDni(dni)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "dni", dni));
	}
	
	@PostMapping
	public ResponseEntity<Paciente> create(@Valid @RequestBody Paciente p) {
		
		Paciente paciente = new Paciente();
		
		// control unicidad de dni
		if(pacienteRepository.findByDni(p.getDni()).isPresent())
			throw new CampoUnicoException("Paciente", "dni", p.getDni());
		
		try {
			paciente = pacienteRepository.save(p);
		} catch (ErrorInternoServidorException e) {
			throw new ErrorInternoServidorException("guardar", "Paciente", p.getId(), e.getMessage());
		}
		
        return new ResponseEntity<Paciente>(paciente, HttpStatus.CREATED);
    }
	
	@PutMapping("/{id}")	
	public ResponseEntity<Paciente> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Paciente p) {

		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));

		// control unicidad de dni
		if(pacienteRepository.findByDni(p.getDni()).isPresent() && paciente.getId() == p.getId())
			throw new CampoUnicoException("Paciente", "dni", p.getDni());

		try {
			paciente.setDni(p.getDni());
			paciente.setNombre(p.getNombre());
			paciente.setApellidos(p.getApellidos());
			paciente.setNacimiento(p.getNacimiento());
			pacienteRepository.save(paciente);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Paciente", id, e.getMessage());
		}
		
		return new ResponseEntity<Paciente>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    Paciente paciente = pacienteRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));

	    try {
	    	pacienteRepository.delete(paciente);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Paciente", id, e.getMessage());
		}

	    return ResponseEntity.ok().build();
	}

}
