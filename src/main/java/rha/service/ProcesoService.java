package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Cura;
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
	
	public List<Cura> findByProceso(long id) {
		Proceso proceso = procesoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Proceso", "id", id));
		return proceso.getCuras();
	}
	
	public Proceso findById(long id) {
		return procesoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Proceso", "id", id));
	}
	
	public List<Proceso> findAllByPacienteId(long id) {
		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));
				
		return procesoRepository.findByPaciente(paciente);
	}

	public ResponseEntity<Proceso> create(Proceso p) {
		Paciente paciente = pacienteRepository.findById(p.getPaciente().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", p.getPaciente().getId()));
		
		p.setPaciente(paciente);
		
		try {
			return new ResponseEntity<Proceso>(procesoRepository.save(p), HttpStatus.CREATED);
		} catch (ErrorInternoServidorException e) {
			throw new ErrorInternoServidorException("guardar", "Proceso", p.getId(), e.getMessage());
		}
		
	}

	public ResponseEntity<Proceso> update(long id, Proceso p) {

		Proceso proceso = procesoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));

		try {
			proceso.setAnamnesis(p.getAnamnesis());
			proceso.setDiagnostico(p.getDiagnostico());
			proceso.setObservaciones(p.getObservaciones());
			procesoRepository.save(proceso);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Proceso", id, e.getMessage());
		}
		
		return new ResponseEntity<Proceso>(proceso, HttpStatus.OK);
	}
	
	public ResponseEntity<?> delete(long id) {
	    Proceso proceso = procesoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Proceso", "id", id));

	    try {
	    	procesoRepository.delete(proceso);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Proceso", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
