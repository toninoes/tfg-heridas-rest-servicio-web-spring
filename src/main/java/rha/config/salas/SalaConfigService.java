package rha.config.salas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Sala;
import rha.repository.SalaRepository;

@Service
public class SalaConfigService {

	@Autowired 
	private SalaRepository salaRepository;
	
	@Autowired
	private SalaConfigRepository salaConfigRepository;

	
	public ResponseEntity<SalaConfig> create(long salaId, SalaConfig sC) {
		Sala sala = salaRepository.findById(salaId)
				.orElseThrow(() -> new RecursoNoEncontradoException("Sala", "id", sC.getSala().getId()));
		
		sC.setSala(sala);
		
		try {
			return new ResponseEntity<SalaConfig>(salaConfigRepository.save(sC), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "SalaConfig", sC.getId(), e.getMessage());
		}
    }
	
	public ResponseEntity<SalaConfig> update(long id, SalaConfig sC) {
		SalaConfig salaConfig = salaConfigRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("SalaConfig", "id", id));

		try {
			salaConfig.setCupo(sC.getCupo());
			salaConfig.setHoraini(sC.getHoraini());
			salaConfig.setMinini(sC.getMinini());
			salaConfig.setLunes(sC.getLunes());
			salaConfig.setMartes(sC.getMartes());
			salaConfig.setMiercoles(sC.getMiercoles());
			salaConfig.setJueves(sC.getJueves());
			salaConfig.setViernes(sC.getViernes());
			salaConfig.setSabado(sC.getSabado());
			salaConfig.setDomingo(sC.getDomingo());
			return new ResponseEntity<SalaConfig>(salaConfigRepository.save(salaConfig), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "SalaConfig", id, e.getMessage());
		}
	}
	
}
