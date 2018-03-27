package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rest.exception.ErrorInternoServidorException;
import rest.exception.RecursoNoEncontradoException;
import rest.model.Cura;
import rest.model.Paciente;
import rest.repository.CuraRepository;
import rest.storage.StorageService;

@RestController
@RequestMapping("/custom/curas")
public class CuraController {
	
	private final CuraRepository curaRepository;
	private final StorageService storageService;
	
	@Autowired
	public CuraController(CuraRepository curaRepository, StorageService storageService) {
		this.curaRepository = curaRepository;
		this.storageService = storageService;
	}
	
	/*@GetMapping
	public List<Cura> findAll() {
		return curaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Cura findById(@PathVariable long id) {
		return curaRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));
	}*/
	
	@PutMapping("/{id}")	
	public ResponseEntity<Paciente> update(@PathVariable(value = "id") Long id, 
			@RequestParam("file") MultipartFile mpf) {
		
		FicheroController fichero = new FicheroController(storageService);
		Cura cura = curaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Paciente", "id", id));

		try {
			cura.setFoto(fichero.subirFichero(mpf));
			curaRepository.save(cura);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("Actualizar", "Paciente", id, e.getMessage());
		}
		
		return new ResponseEntity<Paciente>(HttpStatus.OK);
	}
	
	

}
