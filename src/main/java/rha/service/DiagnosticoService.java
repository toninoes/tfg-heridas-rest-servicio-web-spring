package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Diagnostico;
import rha.model.Grupodiagnostico;
import rha.repository.DiagnosticoRepository;
import rha.repository.GrupodiagnosticoRepository;

@Service
public class DiagnosticoService {

	@Autowired
	private DiagnosticoRepository diagnosticoRepository;
	
	@Autowired
	private GrupodiagnosticoRepository grupoRepository;
	
	//@Cacheable("diagnosticos")
	public List<Diagnostico> findAll() {
		return diagnosticoRepository.findAll();
	}
	
	public List<Diagnostico> findByFiltro(String texto) {
		return diagnosticoRepository.findByFiltroContainingIgnoreCase(texto);
		
	}
	
	//@Cacheable("diagnostico")
	public Diagnostico findById(long id) {
		return diagnosticoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Diagnostico", "id", id));
	}
	
	public ResponseEntity<Diagnostico> create(Diagnostico d) {
		Grupodiagnostico grupodiagnostico = grupoRepository.findById(d.getGrupodiagnostico().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Grupodiagnostico", "id", d.getGrupodiagnostico().getId()));
		
		d.setGrupodiagnostico(grupodiagnostico);
		
		try {
			return new ResponseEntity<Diagnostico>(diagnosticoRepository.save(d), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "Diagnostico", d.getId(), e.getMessage());
		}
    }
	
	public ResponseEntity<Diagnostico> update(long id, Diagnostico d) {
		Diagnostico diagnostico = diagnosticoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Diagnostico", "id", id));

		try {
			diagnostico.setCodigo(d.getCodigo());
			diagnostico.setNombre(d.getNombre());
			return new ResponseEntity<Diagnostico>(diagnosticoRepository.save(diagnostico), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Diagnostico", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
		Diagnostico diagnostico = diagnosticoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Diagnostico", "id", id));

	    try {
	    	diagnosticoRepository.delete(diagnostico);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Diagnostico", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
