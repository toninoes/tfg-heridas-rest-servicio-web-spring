package rha.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/{id}")
	public Diagnostico findById(@PathVariable long id) {
		return diagnosticoService.findById(id);
	}
}
