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
import rha.model.Sanitario;
import rha.repository.CuraRepository;
import rha.repository.PacienteRepository;
import rha.repository.ProcesoRepository;
import rha.repository.SanitarioRepository;

@Service
public class CuraService {
	
	@Autowired
	private CuraRepository curaRepository;
	
	@Autowired
	private ProcesoRepository procesoRepository;
	
	@Autowired
	private SanitarioRepository sanitarioRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
		
	public List<Cura> findAll() {
		return curaRepository.findAll();
	}
	
	public Cura findById(long id) {
		return curaRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));
	}
	
	public ResponseEntity<Cura> create(long sanitarioId, Cura c) {
		Proceso proceso = procesoRepository.findById(c.getProceso().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Proceso", "id", c.getProceso().getId()));
	
		Sanitario sanitario = sanitarioRepository.findById(sanitarioId)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Sanitario", "id", sanitarioId));

		c.setProceso(proceso);
		c.setSanitario(sanitario);
		
		try {
			return new ResponseEntity<Cura>(curaRepository.save(c), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "Cura", c.getId(), e.getMessage());
		}
    }
	
	public ResponseEntity<Cura> update(long id, Cura c) {
		Cura cura = curaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));

		try {
			cura.setEvolucion(c.getEvolucion());
			cura.setRecomendaciones(c.getRecomendaciones());
			cura.setTratamiento(c.getTratamiento());
			return new ResponseEntity<Cura>(curaRepository.save(cura), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Cura", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
	    Cura cura = curaRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));

	    try {
	    	curaRepository.delete(cura);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Cura", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public List<Cura> findByPacienteIdAndNotValoradas(long id) {
		Paciente paciente = pacienteRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));
		
		Boolean valorada = false;
		
		
		return curaRepository.getCurasPacienteIdAndNotValoradas(paciente, valorada);
	}
}
