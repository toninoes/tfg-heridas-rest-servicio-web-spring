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

import rha.model.Diagnostico;
import rha.service.DiagnosticoService;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoRestController {

	@Autowired
	private DiagnosticoService diagnosticoService;
	
	@GetMapping
	public List<Diagnostico> findAll() {
		return diagnosticoService.findAll();
	}
	
	@GetMapping("/filtro")
	public List<Diagnostico> findByFiltro(@RequestParam(value = "filtro", required = false) String filtro) {
		return diagnosticoService.findByFiltro(filtro);
	}
	
	@GetMapping("/g/{id}")
	public List<Diagnostico> findByGrupoDiagnostico(@PathVariable long id) {
		return diagnosticoService.findByGrupoDiagnostico(id);
	}
	
	
	@GetMapping("/{id}")
	public Diagnostico findById(@PathVariable long id) {
		return diagnosticoService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Diagnostico> create(@Valid @RequestBody Diagnostico d) {
		return diagnosticoService.create(d);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Diagnostico> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Diagnostico d) {
		return diagnosticoService.update(id, d);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return diagnosticoService.delete(id);
	}
}
