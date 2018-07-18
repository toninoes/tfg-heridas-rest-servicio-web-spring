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

import rha.model.Procedimiento;
import rha.service.ProcedimientoService;

@RestController
@RequestMapping("/api/procedimientos")
public class ProcedimientoRestController {
	
	@Autowired
	private ProcedimientoService procedimientoService;
	
	@GetMapping
	public List<Procedimiento> findAll() {
		return procedimientoService.findAll();
	}
	
	@GetMapping("/filtro")
	public List<Procedimiento> findByFiltro(@RequestParam(value = "filtro", required = false) String filtro) {
		return procedimientoService.findByFiltro(filtro);
	}

	@GetMapping("/{id}")
	public Procedimiento findById(@PathVariable long id) {
		return procedimientoService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Procedimiento> create(@Valid @RequestBody Procedimiento p) {
		return procedimientoService.create(p);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Procedimiento> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Procedimiento p) {
		return procedimientoService.update(id, p);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return procedimientoService.delete(id);
	}
}
