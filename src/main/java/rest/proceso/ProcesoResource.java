package rest.proceso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rest.exception.RecursoNoEncontradoException;
import rest.paciente.Paciente;
import rest.paciente.PacienteRepository;

@RestController
public class ProcesoResource {
	
	@Autowired
	private ProcesoRepository procesoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	/* 
	 * Obtiene todos los Procesos
	 * GET /procesos
	 */
	@GetMapping("/procesos")
	public List<Proceso> obtenerTodosProcesos() {
		return procesoRepository.findAll();
	}
	
	/* 
	 * Obtiene todos los Procesos de un Paciente
	 * GET /procesos/paciente/{id}
	 */
	@GetMapping("/procesos/paciente/{id}")
	public List<Proceso> obtenerTodosProcesosByPaciente(@PathVariable long id) {
		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));
				
		return procesoRepository.findByPaciente(paciente);
	}
	
	/* 
	 * Obtiene un Proceso según su id
	 * GET /procesos/{id}
	 */
	@GetMapping("/procesos/{id}")
	public Proceso obtenerUnProceso(@PathVariable long id) {
		return procesoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Proceso", "id", id));
	}

}
