package rha.service;

import java.util.ArrayList;
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
import rha.model.Sanitario;
import rha.repository.CitaRepository;
import rha.repository.PacienteRepository;
import rha.repository.SalaRepository;
import rha.repository.SanitarioRepository;

@Service
public class CitaService {
	
	private Date hoy = getHoySinTiempo();
	
	@Autowired
	private CitaRepository citaRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private SalaRepository salaRepository;
	
	@Autowired
	private SanitarioRepository sanitarioRepository;
	
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
		Date fechaSolicitada = FechaSinTiempo(c.getFecha());
		if(fechaSolicitada.before(hoy) || fechaSolicitada.equals(hoy))
			throw new RegistroException("Tienes que indicar una fecha posterior a la actual.");
						
		Paciente paciente = pacienteRepository.findById(c.getPaciente().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", c.getPaciente().getId()));
		
		Sala sala = salaRepository.findById(c.getSala().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Sala", "id", c.getSala().getId()));
		
		if(paciente.getCentroActual().getId() != sala.getCentro().getId())
			throw new RegistroException("Error. El paciente no pertenece a ese Centro.");
		
		Long countProximasCitasPacienteEnEsaSala = citaRepository.countProximasCitasPacienteEnEsaSala(sala, hoy, paciente);
		if(countProximasCitasPacienteEnEsaSala > 0)
			throw new RegistroException("Ya tienes próximas citas asignadas en esa sala.");

		SalaConfig sC = sala.getSalaConfig();
		if(sC == null)
			throw new RegistroException("Esta Sala no está aún disponible para recibir citas.");
		
		Boolean disponible = disponibilidad(sC, fechaSolicitada);
		if (!disponible)
			throw new RegistroException("El día indicado no está disponible.");
			
		Long countBySalaAndFecha = citaRepository.countBySalaAndFecha(sala, fechaSolicitada);
		if (countBySalaAndFecha >= sC.getCupo())
			throw new RegistroException("El día indicado está completo de citas para esa sala.");
		
		if(c.getOrden() > sC.getCupo())
			throw new RegistroException("Error: Nº de orden(" + c.getOrden() +
					") mayor que el cupo disponible en esa sala(" + sC.getCupo() + ").");
		
		Long countBySalaAndFechaAndOrden = citaRepository.countBySalaAndFechaAndOrden(sala, 
				fechaSolicitada, c.getOrden());
		if (countBySalaAndFechaAndOrden > 0)
			throw new RegistroException("Ya hay una cita reservada para ese día y hora.");
				
		try {
			c.setPaciente(paciente);
			c.setSala(sala);
			return new ResponseEntity<Cita>(citaRepository.save(c), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "Cita", c.getId(), e.getMessage());
		}
	}
	
	public synchronized ResponseEntity<Cita> update(long id, Cita c) {
		Cita cita = citaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cita", "id", id));
		Date fechaSolicitada = FechaSinTiempo(c.getFecha());
		if(cita.getFecha().before(hoy))
			throw new RegistroException("No puedes modificar una cita pasada.");
		else if(fechaSolicitada.before(hoy) || fechaSolicitada.equals(hoy))
			throw new RegistroException("Tienes que indicar una fecha posterior a la actual.");
		
		Paciente paciente = pacienteRepository.findById(c.getPaciente().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", c.getPaciente().getId()));
		
		if(cita.getPaciente().getId() != paciente.getId())
			throw new RegistroException("Error. Está intentando modificar una cita que no le pertenece.");
		
		Sala sala = salaRepository.findById(c.getSala().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Sala", "id", c.getSala().getId()));
		
		if(cita.getSala().getId() != sala.getId())
			throw new RegistroException("Error. No está permitido modificar la sala de esta cita.");
		
		if(paciente.getCentroActual().getId() != sala.getCentro().getId())
			throw new RegistroException("Error. El paciente no pertenece a ese Centro.");
		
		SalaConfig sC = sala.getSalaConfig();
		if(sC == null)
			throw new RegistroException("Esta Sala no está aún disponible para recibir citas.");
		
		Boolean disponible = disponibilidad(sC, fechaSolicitada);
		if (!disponible)
			throw new RegistroException("El día indicado no está disponible.");
		
		Long citasBySalaAndFecha = citaRepository.countBySalaAndFecha(sala, fechaSolicitada);
		Long cupoSala = sC.getCupo();
		if (citasBySalaAndFecha >= cupoSala)
			throw new RegistroException("El día indicado está completo de citas para esa sala.");
		
		if(c.getOrden() > sC.getCupo())
			throw new RegistroException("Error: Nº de orden(" + c.getOrden() +
					") mayor que el cupo disponible en esa sala(" + sC.getCupo() + ").");
		
		Long countBySalaAndFechaAndOrden = citaRepository.countBySalaAndFechaAndOrden(sala, 
				fechaSolicitada, c.getOrden());
		Long countByIdAndSalaAndFechaAndOrden = citaRepository.countByIdAndSalaAndFechaAndOrden(id, sala, 
				fechaSolicitada, c.getOrden());
		if (countByIdAndSalaAndFechaAndOrden > 0)
			System.out.println("REGISTRO NO MODIFICADO");
		else if (countBySalaAndFechaAndOrden > 0)
			throw new RegistroException("Ya hay una cita reservada para ese día y hora por otro paciente.");
		
		try {
			cita.setOrden(c.getOrden());
			cita.setFecha(fechaSolicitada);
			return new ResponseEntity<Cita>(citaRepository.save(cita), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Cita", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
	    Cita cita = citaRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cita", "id", id));
	    
		if(cita.getFecha().before(hoy))
			throw new RegistroException("No puedes eliminar una cita pasada.");

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

	public List<Cita> findRealizadasByPacienteId(long pacienteId) {
		Paciente paciente = pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "pacienteId", pacienteId));
		
		return citaRepository.findAllByPacienteOrderByIdDesc(paciente);
	}

	public List<Cita> findPosiblesByPacienteAndSalaAndFecha(Cita c) {
		Date fechaSolicitada = FechaSinTiempo(c.getFecha());
		if(fechaSolicitada.before(hoy) || fechaSolicitada.equals(hoy))
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
		
		Boolean disponible = disponibilidad(sC, fechaSolicitada);
		if (!disponible)
			throw new RegistroException("El día indicado no está disponible.");
		
		Long countBySalaAndFecha = citaRepository.countBySalaAndFecha(sala, fechaSolicitada);
		if (countBySalaAndFecha >= sC.getCupo())
			throw new RegistroException("El día indicado está completo de citas para esa sala.");
		
		List<Long> citasRealizadas = citaRepository.findCitasRealizadasByFechaAndSala(sala, fechaSolicitada);
		List<Cita> posiblesCitas = new ArrayList<Cita>();
		Long cupo = sC.getCupo();
		for(Long i=(long) 1; i<=cupo; i++) {
			if(!citasRealizadas.contains(i))
				posiblesCitas.add(new Cita(paciente, sala, fechaSolicitada, i));
		}
		
		return posiblesCitas;
	}

	public List<Cita> findAgendaBySanitarioId(long sanitarioId, Cita c) {
		Date fechaSolicitada = FechaSinTiempo(c.getFecha());
		Sanitario sanitario = sanitarioRepository.findById(sanitarioId)
				.orElseThrow(() -> new RecursoNoEncontradoException("Sanitario", "sanitarioId", sanitarioId));

		Sala sala = salaRepository.findById(c.getSala().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Sala", "id", c.getSala().getId()));
		
		if(sanitario.getCentroActual().getId() != sala.getCentro().getId())
			throw new RegistroException("Error. El sanitario no pertenece a ese Centro.");
				
		return citaRepository.findAllBySalaAndFechaOrderByIdDesc(sala, fechaSolicitada);
	}
	
	private Date getHoySinTiempo() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	private Date FechaSinTiempo(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}

}
