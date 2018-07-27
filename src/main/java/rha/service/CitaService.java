package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Cita;
import rha.model.Paciente;
import rha.model.Sala;
import rha.repository.CitaRepository;
import rha.repository.PacienteRepository;
import rha.repository.SalaRepository;

@Service
public class CitaService {
	
	@Autowired
	private CitaRepository citaRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private SalaRepository salaRepository;
	
	public List<Cita> findAll() {
		return citaRepository.findAll();
	}
	
	public Cita findById(long id) {
		return citaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cita", "id", id));
	}
	
	public List<Cita> findRecientes() {
		return citaRepository.findTop10ByOrderByIdDesc();
	}
	
	public ResponseEntity<Cita> create(Cita c) {
		Paciente paciente = pacienteRepository.findById(c.getPaciente().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", c.getPaciente().getId()));
		
		Sala sala = salaRepository.findById(c.getSala().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Sala", "id", c.getSala().getId()));
		
		c.setPaciente(paciente);
		c.setSala(sala);
		
		try {
			return new ResponseEntity<Cita>(citaRepository.save(c), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "Cita", c.getId(), e.getMessage());
		}
	}
	
	public ResponseEntity<Cita> update(long id, Cita c) {
		Cita cita = citaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));

		try {
			cita.setFecha(c.getFecha());
			cita.setSala(c.getSala());
			return new ResponseEntity<Cita>(citaRepository.save(cita), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Cita", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
	    Cita cita = citaRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cita", "id", id));

	    try {
	    	citaRepository.delete(cita);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Cita", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
