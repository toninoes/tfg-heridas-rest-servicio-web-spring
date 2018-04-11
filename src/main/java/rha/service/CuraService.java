package rha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rha.exception.ErrorInternoServidorException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Cura;
import rha.repository.CuraRepository;

@Service
public class CuraService {
	
	@Autowired
	private CuraRepository curaRepository;
		
	public List<Cura> findAll() {
		return curaRepository.findAll();
	}
	
	public Cura findById(long id) {
		return curaRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));
	}
	
	public ResponseEntity<Cura> create(Cura c) {
		Cura cura = new Cura();
		
		try {
			cura = curaRepository.save(c);
		} catch (ErrorInternoServidorException e) {
			throw new ErrorInternoServidorException("guardar", "Cura", c.getId(), e.getMessage());
		}
		
        return new ResponseEntity<Cura>(cura, HttpStatus.CREATED);
    }
	
	public ResponseEntity<Cura> update(long id, Cura c) {
		Cura cura = curaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));

		try {
			cura.setEvolucion(c.getEvolucion());
			cura.setProceso(c.getProceso());
			cura.setRecomendaciones(c.getRecomendaciones());
			cura.setTratamiento(c.getTratamiento());
			curaRepository.save(cura);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Cura", id, e.getMessage());
		}
		
		return new ResponseEntity<Cura>(cura, HttpStatus.OK);
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
