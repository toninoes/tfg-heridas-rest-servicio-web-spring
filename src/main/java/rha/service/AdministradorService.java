package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Administrador;
import rha.repository.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired
	private AdministradorRepository administradorRepository;
	
	public List<Administrador> findAll() {
		return administradorRepository.findAll();
	}
	
	public List<Administrador> findByFiltro(String texto) {
		return administradorRepository.findByFiltroContainingIgnoreCase(texto);
		
	}
	
	public Administrador findById(long id) {
		return administradorRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Administrador", "id", id));
	}
	
	public Administrador findByDni(String dni) {
		return administradorRepository.findByDni(dni)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Administrador", "dni", dni));
	}
	
	public ResponseEntity<?> delete(long id) {
		Administrador administrador = administradorRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Administrador", "id", id));

	    try {
	    	administradorRepository.delete(administrador);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Administrador", id);
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
