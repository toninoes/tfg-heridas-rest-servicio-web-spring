package rha.controller.api;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rha.model.Grupodiagnostico;
import rha.service.GrupodiagnosticoService;

@RestController
@RequestMapping("/api/gruposdiagnosticos")
public class GrupodiagnosticoRestController {
	
	@Autowired 
	private GrupodiagnosticoService grupodiagnosticoService;
	
	@GetMapping
	public List<Grupodiagnostico> findAll() {
		return grupodiagnosticoService.findAll();
	}
	
	@GetMapping("/filtro")
	public List<Grupodiagnostico> findByFiltro(@RequestParam(value = "filtro", required = false) String filtro) {
		return grupodiagnosticoService.findByFiltro(filtro);
	}
	
	@GetMapping("/{id}")
	public Grupodiagnostico findById(@PathVariable long id) {
		return grupodiagnosticoService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Grupodiagnostico> create(@Valid @RequestBody Grupodiagnostico gd) {
		return grupodiagnosticoService.create(gd);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Grupodiagnostico> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Grupodiagnostico gd) {
		return grupodiagnosticoService.update(id, gd);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return grupodiagnosticoService.delete(id);
	}

}
