package rha.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Sanitario;
import rha.model.Valoracion;
import rha.model.mapping.DosFechas;
import rha.model.mapping.ValoracionesResults;
import rha.repository.SanitarioRepository;
import rha.repository.ValoracionRepository;

@Service
public class ValoracionService {

	@Autowired
	private ValoracionRepository valoracionRepository;
	
	@Autowired
	private SanitarioRepository sanitarioRepository;
	
	public List<Valoracion> findAll() {
		return valoracionRepository.findAllByOrderByIdDesc();
	}
	
	public List<Valoracion> findByFiltro(String texto) {
		return valoracionRepository.findByFiltroContainingIgnoreCase(texto);
		
	}	
	
	public List<Valoracion> findRecientes() {
		return valoracionRepository.findTop10ByOrderByIdDesc();
	}
	
	public List<ValoracionesResults> findAvgByFiltro(String texto) {
		return valoracionRepository.findAvgByFiltroContainingIgnoreCase(texto);
		
	}	
	
	public Valoracion findById(long id) {
		return valoracionRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Valoracion", "id", id));
	}
	
	public List<Valoracion> findBySanitarioId(long id) {
		Sanitario sanitario = sanitarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Sanitario", "id", id));
		
		return valoracionRepository.findBySanitarioOrderByIdDesc(sanitario);
	}
	
	public List<ValoracionesResults> valoracionesMedias() {
		return valoracionRepository.findAvgNotaBySanitario();
	}
	
	public ResponseEntity<Valoracion> create(Valoracion v) {
		if(v.getNota() < 0 || v.getNota() > 10) {
			throw new ErrorInternoServidorException("guardar", "Valoracion", v.getNota(), 
					"Nota debe ser un valor entre 0 y 10");
		}
		
		if(v.getObservaciones().length() > 280) {
			throw new ErrorInternoServidorException("guardar", "Valoracion", v.getNota(), 
					"Observaciones debe tener una longitud máxima de 280 caracteres.");
		}
			
		Sanitario sanitario = sanitarioRepository.findById(v.getSanitario().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Sanitario", "id", v.getSanitario().getId()));
		
		v.setSanitario(sanitario);
		
		try {
			return new ResponseEntity<Valoracion>(valoracionRepository.save(v), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "Valoracion", v.getId(), e.getMessage());
		}
    }

	public List<ValoracionesResults> findAvgNotaBySanitarioPeriodico(DosFechas df) {
		Date fecha1 = df.getFecha1();
		Date fecha2 = df.getFecha2();
		return valoracionRepository.findAvgNotaBySanitarioPeriodico(fecha1, fecha2);
	}
	
	
}
