package rest.paciente;

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
import org.springframework.web.bind.annotation.RestController;

import rest.exception.RecursoNoEncontradoException;


@RestController
public class PacienteResource {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	/* 
	 * Obtiene todos los Pacientes 
	 * GET /pacientes
	 */
	@GetMapping("/pacientes")
	public List<Paciente> obtenerTodosPacientes() {
		return pacienteRepository.findAll();
	}
	
	/* 
	 * Obtiene un Paciente según su id
	 * GET /pacientes/{id}
	 */
	@GetMapping("/pacientes/{id}")
	public Paciente obtenerUnPaciente(@PathVariable long id) {
		return pacienteRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));
	}
	
	/* 
	 * Crea un nuevo Paciente
	 * POST /pacientes
	 */
	@PostMapping("/pacientes")
	public Paciente crearPaciente(@Valid @RequestBody Paciente paciente) {
	    return pacienteRepository.save(paciente);
	}
	
	/*
	 * Actualiza los datos de un Paciente dado su id
	 * PUT /pacientes/{id}
	 */
	@PutMapping("/pacientes/{id}")
	public Paciente actualizarPaciente(@PathVariable(value = "id") Long id, 
			@Valid @RequestBody Paciente detallesPaciente) {

		Paciente paciente = pacienteRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));

		paciente.setDni(detallesPaciente.getDni());
		paciente.setNombre(detallesPaciente.getNombre());
		paciente.setApellidos(detallesPaciente.getApellidos());
		paciente.setNacimiento(detallesPaciente.getNacimiento());

	    Paciente updatedNote = pacienteRepository.save(paciente);
	    return updatedNote;
	}
	
	/*
	 * Borra un Paciente dado su id
	 * DELETE /pacientes/{id}
	 */
	@DeleteMapping("/pacientes/{id}")
	public ResponseEntity<?> borrarPaciente(@PathVariable(value = "id") Long id) {
	    Paciente paciente = pacienteRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));

	    pacienteRepository.delete(paciente);

	    return ResponseEntity.ok().build();
	}
}
