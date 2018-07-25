package rha.controller.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rha.model.Centro;
import rha.model.UserCentro;
import rha.service.UserCentroService;

@RestController
@RequestMapping("/api/usercentros")
public class UserCentroRestController {
	
	@Autowired
	private UserCentroService usercentroService;
	
	@GetMapping
	public List<UserCentro> findAll() {
		return usercentroService.findAll();
	}
	
	@GetMapping("/{id}")
	public UserCentro findById(@PathVariable long id) {
		return usercentroService.findById(id);
	}
	
	@GetMapping("/user/{userId}")
	public Optional<Centro> getCentroActualByUserId(@PathVariable long userId) {
		return usercentroService.getCentroActualByUserId(userId);
	}
	
	@PostMapping
	public ResponseEntity<UserCentro> create(@Valid @RequestBody UserCentro uc) {
		return usercentroService.create(uc);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<UserCentro> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UserCentro uc) {
		return usercentroService.update(id, uc);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return usercentroService.delete(id);
	}

}
