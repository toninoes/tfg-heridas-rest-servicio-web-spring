package rha.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rha.model.Cura;
import rha.service.CuraService;

@RestController
@RequestMapping("/api/curas")
public class CuraRestController {
	
	@Autowired
	private CuraService curaService;
	
	@GetMapping
	public List<Cura> findAll() {
		return curaService.findAll();
	}
	
	@GetMapping("/{id}")
	public Cura findById(@PathVariable long id) {
		return curaService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Cura> create(@Valid @RequestBody Cura c) {
		return curaService.create(c);
    }


	@PutMapping("/{id}")	
	public ResponseEntity<Cura> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Cura c) {
		return curaService.update(id, c);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return curaService.delete(id);
	}
	
	/*
	@Autowired
	private CuraRepository curaRepository;
		
	
	@GetMapping("/foto/{id}")
	public ResponseEntity<Resource> fotoByCuraId(@PathVariable long id) {
		
		String foto = curaRepository.fotoByCuraId(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Fotografia", " Cura.id", id));
		
		ImagenService fichero = new ImagenService();
		
		return fichero.descargar(foto);
	}
	
	@PutMapping("/{id}")	
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
