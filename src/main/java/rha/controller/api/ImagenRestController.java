package rha.controller.api;

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
	
	/*@PostMapping
    public ResponseEntity<?> subir(@RequestParam("imagen") MultipartFile img, @RequestParam("curaId") long id) {
        return imagenService.subir(img, id);
    }*/
	
	@PostMapping
    public ResponseEntity<?> subir(@RequestParam("imagen") MultipartFile img) {
        return imagenService.subirsinCura(img);
    }
	
	@PostMapping("/{CuraId}")
    public ResponseEntity<?> subirByCuraId(@RequestParam("imagen") MultipartFile img, @PathVariable long CuraId) {
        return imagenService.subir(img, CuraId);
    }
	

}
