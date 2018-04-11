package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.CampoUnicoException;
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
	
	public Paciente findById(long id) {
		return pacienteRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));
	}
	
	public Paciente findByDni(String dni) {
		return pacienteRepository.findByDni(dni)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "dni", dni));
	}
	
	public ResponseEntity<Paciente> create(Paciente p) {		
		// control unicidad de dni
		if(pacienteRepository.findByDni(p.getDni()).isPresent())
			throw new CampoUnicoException("Paciente", "dni", p.getDni());
		
		Paciente paciente = new Paciente();
		
		try {
			paciente = pacienteRepository.save(p);
		} catch (ErrorInternoServidorException e) {
			throw new ErrorInternoServidorException("guardar", "Paciente", p.getId(), e.getMessage());
		}
		
        return new ResponseEntity<Paciente>(paciente, HttpStatus.CREATED);
    }
	
	public ResponseEntity<Paciente> update(long id, Paciente p) {

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
