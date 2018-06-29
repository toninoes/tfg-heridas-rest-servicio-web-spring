package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rha.exception.RecursoNoEncontradoException;
import rha.model.Grupodiagnostico;
import rha.repository.GrupodiagnosticoRepository;

@Service
public class GrupodiagnosticoService {

	@Autowired 
	private GrupodiagnosticoRepository grupodiagnosticoRepository;
	
	public List<Grupodiagnostico> findAll() {
		return grupodiagnosticoRepository.findAll();
	}
	
	public Grupodiagnostico findById(long id) {
		return grupodiagnosticoRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Grupodiagnostico", "id", id));
	}
}
