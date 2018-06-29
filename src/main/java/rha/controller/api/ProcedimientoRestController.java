package rha.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping("/{id}")
	public Procedimiento findById(@PathVariable long id) {
		return procedimientoService.findById(id);
	}
}
