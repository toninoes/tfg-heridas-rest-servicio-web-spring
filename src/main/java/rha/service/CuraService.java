package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Cura;
import rha.model.Proceso;
import rha.repository.CuraRepository;
import rha.repository.ProcesoRepository;

@Service
public class CuraService {
	
	@Autowired
	private CuraRepository curaRepository;
	
	@Autowired
	private ProcesoRepository procesoRepository;
		
	public List<Cura> findAll() {
		return curaRepository.findAll();
	}
	
	public Cura findById(long id) {
		return curaRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));
	}
	
	public ResponseEntity<Cura> create(Cura c) {
		Proceso proceso = procesoRepository.findById(c.getProceso().getId())
				.orElseThrow(() -> new RecursoNoEncontradoException("Proceso", "id", c.getProceso().getId()));
		
		c.setProceso(proceso);
		
		try {
			return new ResponseEntity<Cura>(curaRepository.save(c), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("guardar", "Cura", c.getId(), e.getMessage());
		}
    }
	
	public ResponseEntity<Cura> update(long id, Cura c) {
		Cura cura = curaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));

		try {
			cura.setEvolucion(c.getEvolucion());
			cura.setRecomendaciones(c.getRecomendaciones());
			cura.setTratamiento(c.getTratamiento());
			return new ResponseEntity<Cura>(curaRepository.save(cura), HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Cura", id, e.getMessage());
		}
	}
	
	public ResponseEntity<?> delete(long id) {
	    Cura cura = curaRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));

	    try {
	    	curaRepository.delete(cura);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("borrar", "Cura", id, e.getMessage());
		}

	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	/*
	public ResponseEntity<Resource> fotoByCuraId(@PathVariable long id) {
		
		String foto = curaRepository.fotoByCuraId(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Fotografia", " Cura.id", id));
		
		ImagenService fichero = new ImagenService();
		
		return fichero.descargar(foto);
	}
	
	
	public ResponseEntity<Cura> update(@PathVariable(value = "id") Long id,
			@RequestParam("file") MultipartFile mpf) {
		
		ImagenService fichero = new ImagenService(almacenamientoService);
		Cura curaDeId = curaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));
		
		Optional<Cura> curaDeMpf = curaRepository.findByFoto(mpf.getName());
		
		// control unicidad de nombre de fotografía
		if(curaDeMpf.isPresent() && (curaDeMpf.get().getId() != curaDeId.getId()))
			throw new CampoUnicoException("Cura", "foto", mpf.getName());

		try {
			fichero.borrar(curaDeId.getFoto());
			curaDeId.setFoto(fichero.subir(mpf));
			curaRepository.save(curaDeId);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Cura", id, e.getMessage());
		}
		
		return new ResponseEntity<Cura>(HttpStatus.OK);
	}
	*/

}
