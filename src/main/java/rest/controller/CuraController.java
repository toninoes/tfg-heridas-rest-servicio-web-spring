package rest.controller;

import java.util.Optional;

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

import rest.almacenamiento.Almacenamiento;
import rest.almacenamiento.AlmacenamientoService;
import rest.exception.CampoUnicoException;
import rest.exception.ErrorInternoServidorException;
import rest.exception.RecursoNoEncontradoException;
import rest.model.Cura;
import rest.repository.CuraRepository;

@RestController
@RequestMapping("/custom/curas")
public class CuraController {
	
	private final CuraRepository curaRepository;
	private final AlmacenamientoService almacenamientoService;
	
	@Autowired
	public CuraController(CuraRepository curaRepository, AlmacenamientoService almacenamientoService) {
		this.curaRepository = curaRepository;
		this.almacenamientoService = almacenamientoService;
	}
	
	@GetMapping("/foto/{id}")
	public ResponseEntity<Resource> fotoByCuraId(@PathVariable long id) {
		
		String foto = curaRepository.fotoByCuraId(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Fotografia", " Cura.id", id));
		
		Almacenamiento fichero = new Almacenamiento(almacenamientoService);
		
		return fichero.bajarFichero(foto);
	}
	
	@PutMapping("/{id}")	
	public ResponseEntity<Cura> update(@PathVariable(value = "id") Long id,
			@RequestParam("file") MultipartFile mpf) {
		
		Almacenamiento fichero = new Almacenamiento(almacenamientoService);
		Cura curaDeId = curaRepository.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));
		
		Optional<Cura> curaDeMpf = curaRepository.findByFoto(mpf.getName());
		
		// control unicidad de nombre de fotografía
		if(curaDeMpf.isPresent() && (curaDeMpf.get().getId() != curaDeId.getId()))
			throw new CampoUnicoException("Cura", "foto", mpf.getName());

		try {
			fichero.borrarFichero(curaDeId.getFoto());
			curaDeId.setFoto(fichero.subirFichero(mpf));
			curaRepository.save(curaDeId);
		} catch (Exception e) {
			throw new ErrorInternoServidorException("actualizar", "Cura", id, e.getMessage());
		}
		
		return new ResponseEntity<Cura>(HttpStatus.OK);
	}
	
}
