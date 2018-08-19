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

import rha.model.Grupodiagnostico;
import rha.model.Paciente;
import rha.service.PacienteService;


@RestController
@RequestMapping("/api/pacientes")
public class PacienteRestController {

	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping
	public List<Paciente> findAll() {
		return pacienteService.findAll();
	}
	
	@GetMapping("/filtro")
	public List<Paciente> findByFiltro(@RequestParam(value = "filtro", required = false) String filtro) {
		return pacienteService.findByFiltro(filtro);
	}
	
	@GetMapping("recientes")
	public List<Paciente> findRecientes() {
		return pacienteService.findRecientes();
	}
	
	@GetMapping("/{id}")
	public Paciente findById(@PathVariable long id) {
		return pacienteService.findById(id);
	}
	
	@GetMapping("/dni/{dni}")
	public Paciente findByDni(@PathVariable String dni) {
		return pacienteService.findByDni(dni);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		return pacienteService.delete(id);
	}
	
	@GetMapping("/{id}/gruposdiagnosticos")
	public List<Grupodiagnostico> findGruposdiagnosticosByPaciente(@PathVariable long id) {
		return pacienteService.findGruposdiagnosticosByPaciente(id);
	}

}
