package rha.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/{id}")
	public Grupodiagnostico findById(@PathVariable long id) {
		return grupodiagnosticoService.findById(id);
	}

}
