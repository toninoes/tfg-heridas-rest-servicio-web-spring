package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.CampoUnicoException;
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
	
	public Sanitario findById(long id) {
		return sanitarioRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Sanitario", "id", id));
	}
	
	public Sanitario findByDni(String dni) {
		return sanitarioRepository.findByDni(dni)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Sanitario", "dni", dni));
	}
	
	public ResponseEntity<Sanitario> create(Sanitario s) {		
		// control unicidad de dni
		if(sanitarioRepository.findByDni(s.getDni()).isPresent())
			throw new CampoUnicoException("Sanitario", "dni", s.getDni());
		
		Sanitario sanitario = new Sanitario();
		
		try {
			sanitario = sanitarioRepository.save(s);
		} catch (ErrorInternoServidorException e) {
			throw new ErrorInternoServidorException("guardar", "Sanitario", s.getId(), e.getMessage());
		}
		
        return new ResponseEntity<Sanitario>(sanitario, HttpStatus.CREATED);
    }
	
	public ResponseEntity<Sanitario> update(long id, Sanitario s) {

		Sanitario sanitario = sanitarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Sanitario", "id", id));

		// control unicidad de dni
		if(sanitarioRepository.findByDni(s.getDni()).isPresent() && sanitario.getId() == s.getId())
			throw new CampoUnicoException("Sanitario", "dni", s.getDni());

		try {
			sanitario.setDni(s.getDni());
			sanitario.setColegiado(s.getColegiado());
			sanitario.setFirstname(s.getFirstname());
			sanitario.setLastname(s.getLastname());
			sanitario.setNacimiento(s.getNacimiento());
			sanitarioRepository.save(sanitario);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Sanitario", id, e.getMessage());
		}
		
		return new ResponseEntity<Sanitario>(HttpStatus.OK);
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
