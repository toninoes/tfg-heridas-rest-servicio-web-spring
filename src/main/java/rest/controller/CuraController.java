package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rest.exception.CampoUnicoException;
import rest.exception.ErrorInternoServidorException;
import rest.exception.RecursoNoEncontradoException;
import rest.model.Cura;
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
	
	@GetMapping("/foto/{id}")
	public ResponseEntity<Resource> fotoByCuraId(@PathVariable long id) {
		
		String foto = curaRepository.fotoByCuraId(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Fotografia", " Cura.id", id));
		
		FicheroController fichero = new FicheroController(storageService);
		
		return fichero.bajarFichero(foto);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Cura> update(@PathVariable(value = "id") Long id, 
			@RequestParam("file") MultipartFile mpf) {
		
		FicheroController fichero = new FicheroController(storageService);
		Cura cura = curaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));
		
		// control unicidad de dni
		if(curaRepository.findByFoto(mpf.getName()).isPresent())
			throw new CampoUnicoException("Cura", "foto", mpf.getName());

		try {
			cura.setFoto(fichero.subirFichero(mpf));
			curaRepository.save(cura);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("Actualizar", "Cura", id, e.getMessage());
		}
		
		return new ResponseEntity<Cura>(HttpStatus.OK);
	}
	
}
