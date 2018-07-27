package rha.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rha.model.Valoracion;
import rha.model.ValoracionesResults;
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
	
	@GetMapping("/filtro")
	public List<Valoracion> findByFiltro(@RequestParam(value = "filtro", required = false) String filtro) {
		return valoracionService.findByFiltro(filtro);
	}
	
	@GetMapping("recientes")
	public List<Valoracion> findRecientes() {
		return valoracionService.findRecientes();
	}
	
	@GetMapping("/media/filtro")
	public List<ValoracionesResults> findAvgByFiltro(@RequestParam(value = "filtro", required = false) String filtro) {
		return valoracionService.findAvgByFiltro(filtro);
	}
	
	@GetMapping("/{id}")
	public Valoracion findById(@PathVariable long id) {
		return valoracionService.findById(id);
	}
	
	@GetMapping("/sanitario/{id}")
	public List<Valoracion> findAllBySanitarioId(@PathVariable long id) {			
		return valoracionService.findBySanitarioId(id);
	}
	
	@GetMapping("/media")
	public List<ValoracionesResults> valoracionesMedias() {
		return valoracionService.valoracionesMedias();
	}
	
	@PostMapping
	public ResponseEntity<Valoracion> create(@RequestBody Valoracion v) {
		return valoracionService.create(v);
	}

}
