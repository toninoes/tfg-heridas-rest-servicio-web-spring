package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Sanitario;
import rha.repository.SanitarioRepository;

@Service
public class SanitarioService {

	@Autowired
	private SanitarioRepository sanitarioRepository;
	
	public List<Sanitario> findAll() {
		return sanitarioRepository.findAll();
	}
	
	public List<Sanitario> findByFiltro(String texto) {
		return sanitarioRepository.findByFiltroContainingIgnoreCase(texto);
		
	}
	
	public Sanitario findById(long id) {
		return sanitarioRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Sanitario", "id", id));
	}
	
	public Sanitario findByDni(String dni) {
		return sanitarioRepository.findByDni(dni)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Sanitario", "dni", dni));
	}
	
	public ResponseEntity<?> delete(long id) {
		Sanitario sanitario = sanitarioRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Sanitario", "id", id));

	    try {
	    	sanitarioRepository.delete(sanitario);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Sanitario", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
