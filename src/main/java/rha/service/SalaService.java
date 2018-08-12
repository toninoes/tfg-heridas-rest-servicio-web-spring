package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.jwt.model.security.User;
import rha.jwt.security.repository.UserRepository;
import rha.model.Centro;
import rha.model.Sala;
import rha.repository.CentroRepository;
import rha.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired 
	private SalaRepository salaRepository;
	
	@Autowired
	private CentroRepository centroRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	public List<Sala> findAll() {
		return salaRepository.findAll();
	}
	
	public List<Sala> findByFiltro(String texto) {
		return salaRepository.findByFiltroContainingIgnoreCase(texto);
		
	}
	
	public List<Sala> findRecientes() {
		return salaRepository.findTop10ByOrderByIdDesc();
	}
	
	public Sala findById(long id) {
		return salaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Sala", "id", id) );
	}
	
	public ResponseEntity<Sala> create(Sala s) {
		Centro centro = centroRepository.findById(s.getCentro().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Centro", "id", s.getCentro().getId()));
		
		s.setCentro(centro);
		
		try {
			return new ResponseEntity<Sala>(salaRepository.save(s), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "Sala", s.getId(), e.getMessage());
		}
    }
	
	public ResponseEntity<Sala> update(long id, Sala s) {
		Sala sala = salaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Sala", "id", id));

		try {
			sala.setNombre(s.getNombre());
			sala.setCentro(s.getCentro());
			return new ResponseEntity<Sala>(salaRepository.save(sala), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Sala", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
		Sala sala = salaRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Sala", "id", id));

	    try {
	    	salaRepository.delete(sala);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Sala", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public List<Sala> findByUserId(long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", userId));
		
		return user.getCentroActual().getSalas();
	}
}
