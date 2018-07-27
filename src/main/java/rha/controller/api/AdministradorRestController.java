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

import rha.model.Administrador;
import rha.service.AdministradorService;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorRestController {
	
	@Autowired
	private AdministradorService administradorService;
	
	@GetMapping
	public List<Administrador> findAll() {
		return administradorService.findAll();
	}
	
	@GetMapping("/filtro")
	public List<Administrador> findByFiltro(@RequestParam(value = "filtro", required = false) String filtro) {
		return administradorService.findByFiltro(filtro);
	}
	
	@GetMapping("recientes")
	public List<Administrador> findRecientes() {
		return administradorService.findRecientes();
	}
	
	@GetMapping("/{id}")
	public Administrador findById(@PathVariable long id) {
		return administradorService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return administradorService.delete(id);
	}

}
