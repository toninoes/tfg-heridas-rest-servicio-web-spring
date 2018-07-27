package rha.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rha.model.Sanitario;
import rha.service.SanitarioService;

@RestController
@RequestMapping("/api/sanitarios")
public class SanitarioRestController {
	
	@Autowired
	private SanitarioService sanitarioService;
	
	@GetMapping
	public List<Sanitario> findAll() {
		return sanitarioService.findAll();
	}
	
	@GetMapping("/filtro")
	public List<Sanitario> findByFiltro(@RequestParam(value = "filtro", required = false) String filtro) {
		return sanitarioService.findByFiltro(filtro);
	}
	
	@GetMapping("recientes")
	public List<Sanitario> findRecientes() {
		return sanitarioService.findRecientes();
	}
	
	@GetMapping("/{id}")
	public Sanitario findById(@PathVariable long id) {
		return sanitarioService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return sanitarioService.delete(id);
	}

}
