package rha.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rha.model.Cuidado;
import rha.service.CuidadoService;

@RestController
@RequestMapping("/api/cuidados")
public class CuidadoRestController {
	
	@Autowired
	private CuidadoService cuidadoService;

	@GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Resource> descargar(@PathVariable long id) {
    	return cuidadoService.descargarById(id);
    }
		
	@GetMapping("/diagnostico/{id}")
	public List<Cuidado> findByDiagnosticoId(@PathVariable long id) {
		return cuidadoService.findByDiagnosticoId(id);
	}
	
	@PostMapping
    public ResponseEntity<?> subir(@RequestParam("cuidado") MultipartFile doc) {
        return cuidadoService.subirsinDiagnostico(doc);
    }
	
	@PostMapping("/{DiagnosticoId}")
    public ResponseEntity<?> subirByDiagnosticoId(@RequestParam("cuidado") MultipartFile doc, @PathVariable long DiagnosticoId, 
    		@RequestParam("descripcion") String descripcion) {
        return cuidadoService.subir(doc, DiagnosticoId, descripcion);
    }
	

}
