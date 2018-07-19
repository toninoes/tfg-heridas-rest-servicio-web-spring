package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.CampoUnicoException;
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
	
	public Administrador findById(long id) {
		return administradorRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Administrador", "id", id));
	}
	
	public Administrador findByDni(String dni) {
		return administradorRepository.findByDni(dni)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Administrador", "dni", dni));
	}
	
	public ResponseEntity<Administrador> create(Administrador a) {		
		// control unicidad de dni
		if(administradorRepository.findByDni(a.getDni()).isPresent())
			throw new CampoUnicoException("Administrador", "dni", a.getDni());
		
		Administrador administrador = new Administrador();
		
		try {
			administrador = administradorRepository.save(a);
		} catch (ErrorInternoServidorException e) {
			throw new ErrorInternoServidorException("guardar", "Administrador", a.getId(), e.getMessage());
		}
		
        return new ResponseEntity<Administrador>(administrador, HttpStatus.CREATED);
    }
	
	public ResponseEntity<Administrador> update(long id, Administrador a) {

		Administrador administrador = administradorRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Administrador", "id", id));

		// control unicidad de dni
		if(administradorRepository.findByDni(a.getDni()).isPresent() && administrador.getId() == a.getId())
			throw new CampoUnicoException("Administrador", "dni", a.getDni());

		try {
			administrador.setDni(a.getDni());
			administrador.setFirstname(a.getFirstname());
			administrador.setLastname(a.getLastname());
			administrador.setNacimiento(a.getNacimiento());
			administradorRepository.save(administrador);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Administrador", id, e.getMessage());
		}
		
		return new ResponseEntity<Administrador>(HttpStatus.OK);
	}
	
	public ResponseEntity<?> delete(long id) {
		Administrador administrador = administradorRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Administrador", "id", id));

	    try {
	    	administradorRepository.delete(administrador);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Administrador", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
