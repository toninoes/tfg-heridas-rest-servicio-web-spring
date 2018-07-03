package rha.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rha.model.Valoracion;
import rha.service.ValoracionService;

@RestController
@RequestMapping("/api/valoraciones")
public class ValoracionRestController {
	
	@Autowired
	private ValoracionService valoracionService;
	
	@GetMapping
	public List<Valoracion> findAll() {
		return valoracionService.findAll();
	}
	
	@GetMapping("/{id}")
	public Valoracion findById(@PathVariable long id) {
		return valoracionService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Valoracion> create(@Valid @RequestBody Valoracion v) {
		return valoracionService.create(v);
	}

}
