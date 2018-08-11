package rha.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.config.salas.SalaConfig;
import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.exception.RegistroException;
import rha.model.Cita;
import rha.model.Paciente;
import rha.model.Sala;
import rha.repository.CitaRepository;
import rha.repository.PacienteRepository;
import rha.repository.SalaRepository;

@Service
public class CitaService {
	
	@Autowired
	private CitaRepository citaRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private SalaRepository salaRepository;
	
	public List<Cita> findAll() {
		return citaRepository.findAll();
	}
	
	public Cita findById(long id) {
		return citaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cita", "id", id));
	}
	
	public List<Cita> findRecientes() {
		return citaRepository.findTop10ByOrderByIdDesc();
	}
	
	public synchronized ResponseEntity<Cita> create(Cita c) {
		Date hoy = new Date();
		if(c.getFecha().before(hoy))
			throw new RegistroException("Tienes que indicar una fecha posterior a la actual.");
						
		Paciente paciente = pacienteRepository.findById(c.getPaciente().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", c.getPaciente().getId()));
		
		Sala sala = salaRepository.findById(c.getSala().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Sala", "id", c.getSala().getId()));
		
		if(paciente.getCentroActual().getId() != sala.getCentro().getId())
			throw new RegistroException("Error. El paciente no pertenece a ese Centro.");
		
		Long citasProximasPacienteEnEsaSala = citaRepository.citasProximasPacienteEnEsaSala(sala, hoy, paciente);
		if(citasProximasPacienteEnEsaSala > 0)
			throw new RegistroException("Ya tienes próximas citas asignadas en esa sala.");

		SalaConfig sC = sala.getSalaConfig();
		if(sC == null)
			throw new RegistroException("Esta Sala no está aún disponible para recibir citas.");
		
		Boolean disponible = disponibilidad(sC, c.getFecha());
		Long citasBySalaAndFecha = citaRepository.countBySalaAndFecha(sala, c.getFecha());
		Long cupoSala = sC.getCupo();
		if(disponible && citasBySalaAndFecha < cupoSala) {
			c.setPaciente(paciente);
			c.setSala(sala);
			
			Long maxOrden = citaRepository.findMaxOrden(sala, c.getFecha());
			if(maxOrden != null)
				maxOrden++;
			else
				maxOrden = (long) 1;
			
			c.setOrden(maxOrden);
		} else if (!disponible) {
			throw new RegistroException("El día indicado no está disponible.");
		} else if (citasBySalaAndFecha >= cupoSala) {
			throw new RegistroException("El día indicado está completo de citas para esa sala.");
		}
		
		
		try {
			return new ResponseEntity<Cita>(citaRepository.save(c), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "Cita", c.getId(), e.getMessage());
		}
	}
	
	public ResponseEntity<Cita> update(long id, Cita c) {
		Cita cita = citaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cita", "id", id));
		
		Date hoy = new Date();
		if(cita.getFecha().before(hoy))
			throw new RegistroException("No puedes modificar una cita pasada.");
		else if(c.getFecha().before(hoy))
			throw new RegistroException("Tienes que indicar una fecha posterior a la actual.");
		
		Paciente paciente = pacienteRepository.findById(c.getPaciente().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", c.getPaciente().getId()));
		
		Sala sala = salaRepository.findById(c.getSala().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Sala", "id", c.getSala().getId()));
		
		if(paciente.getCentroActual().getId() != sala.getCentro().getId())
			throw new RegistroException("Error. El paciente no pertenece a ese Centro.");
		
		SalaConfig sC = sala.getSalaConfig();
		if(sC == null)
			throw new RegistroException("Esta Sala no está aún disponible para recibir citas.");
		
		Boolean disponible = disponibilidad(sC, c.getFecha());
		Long citasBySalaAndFecha = citaRepository.countBySalaAndFecha(sala, c.getFecha());
		Long cupoSala = sC.getCupo();
		if(disponible && citasBySalaAndFecha < cupoSala) {		
			Long maxOrden = citaRepository.findMaxOrden(sala, c.getFecha());
			if(maxOrden != null)
				maxOrden++;
			else
				maxOrden = (long) 1;
			
			cita.setOrden(maxOrden);
			cita.setFecha(c.getFecha());
		} else if (!disponible) {
			throw new RegistroException("El día indicado no está disponible.");
		} else if (citasBySalaAndFecha >= cupoSala) {
			throw new RegistroException("El día indicado está completo de citas para esa sala.");
		}
		

		try {
			
			return new ResponseEntity<Cita>(citaRepository.save(cita), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Cita", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
	    Cita cita = citaRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cita", "id", id));

	    try {
	    	citaRepository.delete(cita);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Cita", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	private Boolean disponibilidad(SalaConfig salaConfig, Date fecha) {
		Boolean disponible = false;
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		
		// Domingo: 1; Lunes: 2; Martes: 3; Miércoles: 4; 
		// Jueves: 5; Viernes:6; Sábado: 7
		int diaSemana = c.get(Calendar.DAY_OF_WEEK);
				
		if(diaSemana == 1 && salaConfig.getDomingo())
			disponible = true;
		else if(diaSemana == 2 && salaConfig.getLunes())
			disponible = true;
		else if(diaSemana == 3 && salaConfig.getMartes())
			disponible = true;
		else if(diaSemana == 4 && salaConfig.getMiercoles())
			disponible = true;
		else if(diaSemana == 5 && salaConfig.getJueves())
			disponible = true;
		else if(diaSemana == 6 && salaConfig.getViernes())
			disponible = true;
		else if(diaSemana == 7 && salaConfig.getSabado())
			disponible = true;
		
		return disponible;
	}

	public Long pacientesDelanteMia(long citaId) {
		Cita cita = citaRepository.findById(citaId)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cita", "id", citaId));
		
		Sala sala = cita.getSala();
		Date fecha = cita.getFecha();
		Long orden = cita.getOrden();
		
		return citaRepository.countCuantosHayDelante(sala, fecha, orden);
	}

}
