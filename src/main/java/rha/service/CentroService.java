package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.CampoUnicoException;
import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Centro;
import rha.repository.CentroRepository;

@Service
public class CentroService {

	@Autowired
	private CentroRepository centroRepository;

	public List<Centro> findAll() {
		return centroRepository.findAll();
	}
	
	public List<Centro> findByFiltro(String texto) {
		return centroRepository.findByFiltroContainingIgnoreCase(texto);
		
	}

	public Centro findById(long id) {
		return centroRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Centro", "id", id));
	}
	
	public ResponseEntity<Centro> create(Centro c) {
		// control unicidad de nombre
		if(centroRepository.findByNombre(c.getNombre()).isPresent())
			throw new CampoUnicoException("Centro", "nombre", c.getNombre());
		
		try {
			return new ResponseEntity<Centro>(centroRepository.save(c), HttpStatus.CREATED);
		} catch (ErrorInternoServidorException e) {
			throw new ErrorInternoServidorException("guardar", "Centro", c.getId(), e.getMessage());
		}
	}
	
	public ResponseEntity<Centro> update(long id, Centro c) {
		Centro centro = centroRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Centro", "id", id));
		
		try {
			centro.setNombre(c.getNombre());
			centro.setDireccion(c.getDireccion());
			centro.setTelefono(c.getTelefono());
			return new ResponseEntity<Centro>(centroRepository.save(centro), HttpStatus.OK);			
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Centro", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
	    Centro centro = centroRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Centro", "id", id));

	    try {
	    	centroRepository.delete(centro);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Centro", id);
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
