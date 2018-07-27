package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Paciente;
import rha.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	public List<Paciente> findAll() {
		return pacienteRepository.findAll();
	}
	
	public List<Paciente> findByFiltro(String texto) {
		return pacienteRepository.findByFiltroContainingIgnoreCase(texto);
		
	}
	
	public List<Paciente> findRecientes() {
		return pacienteRepository.findTop10ByOrderByIdDesc();
	}
	
	public Paciente findById(long id) {
		return pacienteRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));
	}
	
	public Paciente findByDni(String dni) {
		return pacienteRepository.findByDni(dni)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "dni", dni));
	}

	public ResponseEntity<?> delete(long id) {
	    Paciente paciente = pacienteRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));

	    try {
	    	pacienteRepository.delete(paciente);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Paciente", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
