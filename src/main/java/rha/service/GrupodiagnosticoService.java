package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Grupodiagnostico;
import rha.repository.GrupodiagnosticoRepository;

@Service
public class GrupodiagnosticoService {

	@Autowired 
	private GrupodiagnosticoRepository grupodiagnosticoRepository;
	
	public List<Grupodiagnostico> findAll() {
		return grupodiagnosticoRepository.findAll();
	}
	
	public Grupodiagnostico findById(long id) {
		return grupodiagnosticoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Grupodiagnostico", "id", id));
	}
	
	public ResponseEntity<Grupodiagnostico> create(Grupodiagnostico gd) {	
		try {
			return new ResponseEntity<Grupodiagnostico>(grupodiagnosticoRepository.save(gd), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "Grupodiagnostico", gd.getId(), e.getMessage());
		}
    }
	
	public ResponseEntity<Grupodiagnostico> update(long id, Grupodiagnostico gd) {
		Grupodiagnostico grupodiagnostico = grupodiagnosticoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Grupodiagnostico", "id", id));

		try {
			grupodiagnostico.setNombre(gd.getNombre());
			return new ResponseEntity<Grupodiagnostico>(grupodiagnosticoRepository.save(grupodiagnostico), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Grupodiagnostico", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
		Grupodiagnostico grupodiagnostico = grupodiagnosticoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Grupodiagnostico", "id", id));

	    try {
	    	grupodiagnosticoRepository.delete(grupodiagnostico);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Grupodiagnostico", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
