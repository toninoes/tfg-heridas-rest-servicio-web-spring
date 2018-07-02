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
import rha.model.UserCentro;
import rha.repository.CentroRepository;
import rha.repository.UserCentroRepository;

@Service
public class UserCentroService {

	@Autowired
	private UserCentroRepository usercentroRepository;
	
	@Autowired
	private CentroRepository centroRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserCentro> findAll() {
		return usercentroRepository.findAll();
	}
	
	public UserCentro findById(long id) {
		return usercentroRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("UserCentro", "id", id));
	}
	
	public ResponseEntity<UserCentro> create(UserCentro uc) {
		Centro centro = centroRepository.findById(uc.getCentro().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Centro", "id", uc.getCentro().getId()));
		
		User user = userRepository.findById(uc.getUser().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", uc.getUser().getId()));
		
		uc.setCentro(centro);
		uc.setUser(user);
		
		try {
			return new ResponseEntity<UserCentro>(usercentroRepository.save(uc), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "UserCentro", uc.getId(), e.getMessage());
		}
    }
	
	public ResponseEntity<UserCentro> update(long id, UserCentro uc) {
		UserCentro usercentro = usercentroRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("UserCentro", "id", id));
		
		Centro centro = centroRepository.findById(uc.getCentro().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Centro", "id", uc.getCentro().getId()));

		try {
			usercentro.setCentro(centro);
			usercentro.setInicio(uc.getInicio());
			usercentro.setFin(uc.getFin());
			return new ResponseEntity<UserCentro>(usercentroRepository.save(usercentro), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "UserCentro", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
		UserCentro usercentro = usercentroRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("UserCentro", "id", id));

	    try {
	    	usercentroRepository.delete(usercentro);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "UserCentro", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
