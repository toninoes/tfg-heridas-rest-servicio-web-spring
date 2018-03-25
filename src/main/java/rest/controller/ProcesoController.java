package rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest.exception.RecursoNoEncontradoException;
import rest.model.Paciente;
import rest.model.Proceso;
import rest.repository.PacienteRepository;
import rest.repository.ProcesoRepository;

@RestController
@RequestMapping("/api/procesos")
public class ProcesoController {
	
	@Autowired
	private ProcesoRepository procesoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@GetMapping
	public List<Proceso> findAll() {
		return procesoRepository.findAll();
	}

	@GetMapping("/paciente/{id}")
	public List<Proceso> findAllByPacienteId(@PathVariable long id) {
		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));
				
		return procesoRepository.findByPaciente(paciente);
	}
	
	@GetMapping("/{id}")
	public Proceso findById(@PathVariable long id) {
		return procesoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Proceso", "id", id));
	}

}
