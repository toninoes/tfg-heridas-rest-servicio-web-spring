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

import rha.model.Imagen;
import rha.service.ImagenService;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenRestController {
	
	@Autowired
	private ImagenService imagenService;

	@GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Resource> descargar(@PathVariable long id) {
    	return imagenService.descargar(id);
    }
	
	@GetMapping("/cura/{id}")
	public List<Imagen> findByCuraId(@PathVariable long id) {
		return imagenService.findByCuraId(id);
	}
	
	@PostMapping
    public ResponseEntity<?> subir(@RequestParam("imagen") MultipartFile img) {
        return imagenService.subirsinCura(img);
    }
	
	@PostMapping("/{CuraId}")
    public ResponseEntity<?> subirByCuraId(@RequestParam("imagen") MultipartFile img, @PathVariable long CuraId, 
    		@RequestParam("descripcion") String descripcion) {
        return imagenService.subir(img, CuraId, descripcion);
    }
	

}
