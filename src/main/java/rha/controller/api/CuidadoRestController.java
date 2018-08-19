package rha.controller.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rha.model.Cuidado;
import rha.model.Grupodiagnostico;
import rha.service.CuidadoService;
import rha.service.GrupodiagnosticoService;
import rha.util.GenerarInformePDF;

@RestController
@RequestMapping("/api/cuidados")
public class CuidadoRestController {
	
	@Autowired
	private CuidadoService cuidadoService;
	
	@Autowired
	private GrupodiagnosticoService grupodiagnosticoService;
	
	@GetMapping
	public List<Cuidado> findAll() {
		return cuidadoService.findAll();
	}
	
	@GetMapping("/grupodiagnostico/{id}")
	public List<Cuidado> findByGDiagnosticoId(@PathVariable long id) {
		return cuidadoService.findByGDiagnosticoId(id);
	}
	
	@GetMapping("/{id}")
	public Cuidado findById(@PathVariable long id) {
		return cuidadoService.findById(id);
	}
	
	@PostMapping("/{sanitarioId}")
	public ResponseEntity<Cuidado> create(@PathVariable long sanitarioId, @RequestBody Cuidado c) {
		return cuidadoService.create(sanitarioId, c);
    }
	
	@PutMapping("/{id}")	
	public ResponseEntity<Cuidado> update(@PathVariable long id, @Valid @RequestBody Cuidado c) {
		return cuidadoService.update(id, c);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
	    return cuidadoService.delete(id);
	}
	

	@GetMapping(value = "/grupodiagnostico/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> findPdfById(@PathVariable long id) throws IOException {
		Grupodiagnostico grupodiagnostico =  grupodiagnosticoService.findById(id);

        ByteArrayInputStream bis = GenerarInformePDF.informeCuidado(grupodiagnostico);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=informe.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
	}
	
	

}
