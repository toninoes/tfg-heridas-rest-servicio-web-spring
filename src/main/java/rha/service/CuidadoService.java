package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Cuidado;
import rha.model.Grupodiagnostico;
import rha.model.Sanitario;
import rha.repository.CuidadoRepository;
import rha.repository.GrupodiagnosticoRepository;
import rha.repository.SanitarioRepository;


@Service
public class CuidadoService {

	
	@Autowired
	private CuidadoRepository cuidadoRepository;
	
	@Autowired
	private SanitarioRepository sanitarioRepository;
	
	@Autowired
	private GrupodiagnosticoRepository grupodiagnosticoRepository;

	public List<Cuidado> findAll() {
		return cuidadoRepository.findAll();
	}
	
	public Cuidado findById(long id) {
		return cuidadoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cuidado", "id", id));
	}
	
	public ResponseEntity<Cuidado> create(long id, Cuidado c) {
		Sanitario sanitario = sanitarioRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Sanitario", "id", id));

		c.setSanitario(sanitario);
		
		try {
			return new ResponseEntity<Cuidado>(cuidadoRepository.save(c), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "Cuidado", c.getId(), e.getMessage());
		}
    }
	
	public ResponseEntity<Cuidado> update(long id, Cuidado c) {
		Cuidado cuidado = cuidadoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cuidado", "id", id));

		try {
			cuidado.setDescripcion(c.getDescripcion());
			cuidado.setNombre(c.getNombre());
			cuidado.setGrupodiagnostico(c.getGrupodiagnostico());
			cuidado.setSanitario(c.getSanitario());
			return new ResponseEntity<Cuidado>(cuidadoRepository.save(cuidado), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Cuidado", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
		Cuidado cuidado = cuidadoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cuidado", "id", id));

	    try {
	    	cuidadoRepository.delete(cuidado);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Cuidado", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public List<Cuidado> findByGDiagnosticoId(long id) {
		Grupodiagnostico grupodiagnostico = grupodiagnosticoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Grupodiagnostico", "id", id));
		
		return cuidadoRepository.findByGrupodiagnostico(grupodiagnostico);
	}

}