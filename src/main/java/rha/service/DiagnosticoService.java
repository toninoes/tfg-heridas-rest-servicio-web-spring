package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rha.exception.RecursoNoEncontradoException;
import rha.model.Diagnostico;
import rha.repository.DiagnosticoRepository;

@Service
public class DiagnosticoService {

	@Autowired
	private DiagnosticoRepository diagnosticoRepository;
	
	public List<Diagnostico> findAll() {
		return diagnosticoRepository.findAll();
	}
	
	public Diagnostico findById(long id) {
		return diagnosticoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Diagnostico", "id", id));
	}
}
