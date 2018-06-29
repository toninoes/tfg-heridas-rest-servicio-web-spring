package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
