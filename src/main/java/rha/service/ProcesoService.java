package rha.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Paciente;
import rha.model.Proceso;
import rha.repository.PacienteRepository;
import rha.repository.ProcesoRepository;

@Service
public class ProcesoService {

	@Autowired
	private ProcesoRepository procesoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public List<Proceso> findAll() {
		return procesoRepository.findAll();
	}
	
	public Proceso findById(@PathVariable long id) {
		return procesoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Proceso", "id", id));
	}
	
	public List<Proceso> findAllByPacienteId(@PathVariable long id) {
		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));
				
		return procesoRepository.findByPaciente(paciente);
	}

	public ResponseEntity<Proceso> create(@Valid @RequestBody Proceso p) {
		pacienteRepository.findById(p.getPaciente().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", p.getPaciente().getId()));
		
		Proceso proceso = new Proceso();
		
		try {
			proceso = procesoRepository.save(p);
		} catch (ErrorInternoServidorException e) {
			throw new ErrorInternoServidorException("guardar", "Proceso", p.getId(), e.getMessage());
		}
		
		return new ResponseEntity<Proceso>(proceso, HttpStatus.CREATED);
	}

	public ResponseEntity<Proceso> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Proceso p) {

		Proceso proceso = procesoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));

		try {
			proceso.setAnamnesis(p.getAnamnesis());
			proceso.setDiagnostico(p.getDiagnostico());
			proceso.setTipo(p.getTipo());
			procesoRepository.save(proceso);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Proceso", id, e.getMessage());
		}
		
		return new ResponseEntity<Proceso>(HttpStatus.OK);
	}
	
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    Proceso proceso = procesoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Proceso", "id", id));

	    try {
	    	procesoRepository.delete(proceso);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Proceso", id, e.getMessage());
		}

	    return ResponseEntity.ok().build();
	}

}