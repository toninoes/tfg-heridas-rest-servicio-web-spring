package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Procedimiento;
import rha.repository.ProcedimientoRepository;

@Service
public class ProcedimientoService {

	@Autowired
	private ProcedimientoRepository procedimientoRepository;
	
	public List<Procedimiento> findAll() {
		return procedimientoRepository.findAll();
	}
	
	public Procedimiento findById(long id) {
		return procedimientoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Procedimiento", "id", id));
	}
	
	public ResponseEntity<Procedimiento> create(Procedimiento p) {	
		try {
			return new ResponseEntity<Procedimiento>(procedimientoRepository.save(p), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "Procedimiento", p.getId(), e.getMessage());
		}
    }
	
	public ResponseEntity<Procedimiento> update(long id, Procedimiento p) {
		Procedimiento procedimiento = procedimientoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Procedimiento", "id", id));

		try {
			procedimiento.setCodigo(p.getCodigo());
			procedimiento.setNombre(p.getNombre());
			return new ResponseEntity<Procedimiento>(procedimientoRepository.save(procedimiento), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Procedimiento", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
		Procedimiento procedimiento = procedimientoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Procedimiento", "id", id));

	    try {
	    	procedimientoRepository.delete(procedimiento);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Procedimiento", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
