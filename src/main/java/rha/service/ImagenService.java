package rha.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import rha.exception.AlmacenamientoFicheroNoEncontradoException;
import rha.exception.RecursoNoEncontradoException;
import rha.model.Cura;
import rha.model.Imagen;
import rha.repository.CuraRepository;
import rha.repository.ImagenRepository;


@Service
public class ImagenService {

	@Autowired
    private AlmacenamientoService almacenamientoService;
	
	@Autowired
	private ImagenRepository imagenRepository;
	
	@Autowired
	private CuraRepository curaRepository;

    @ResponseBody
    public ResponseEntity<Resource> descargar(long id) {
    	Imagen imagen = imagenRepository.findById(id)
    			.orElseThrow(() -> new RecursoNoEncontradoException("Imagen", "id", id));
    	
        Resource file = almacenamientoService.loadAsResource(imagen.getNombre());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    
    public ResponseEntity<?> subir(MultipartFile img, long curaId) {
    	if(esImagen(img)) {
			Cura cura = curaRepository.findById(curaId)
		            .orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", curaId));
			
    		Imagen imagen = new Imagen(img.getOriginalFilename(), cura);
    	
			imagenRepository.save(imagen);
    		
        	almacenamientoService.store(img);
        	
        	return new ResponseEntity<Imagen>(imagen, HttpStatus.CREATED);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    	} 
    }
    
    public ResponseEntity<?> subirsinCura(MultipartFile img)  {
    	if(esImagen(img)) {
    		
    		Imagen imagen = new Imagen(img.getOriginalFilename());
    		imagenRepository.save(imagen);
    		String nuevoNombre = imagen.getId() + "." + obtenerExtension(img);
    		imagen.setNombre(nuevoNombre);    	
			imagenRepository.save(imagen);    		
        	almacenamientoService.store(img, nuevoNombre);
        	
        	return new ResponseEntity<Imagen>(imagen, HttpStatus.CREATED);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    	}
    }
    
    public void borrar(@PathVariable String filename) {
        almacenamientoService.delete(filename);
    }
    

    @ExceptionHandler(AlmacenamientoFicheroNoEncontradoException.class)
    public ResponseEntity<?> handleStorageFileNotFound(AlmacenamientoFicheroNoEncontradoException exc) {
        return ResponseEntity.notFound().build();
    }
    
    private Boolean esImagen(MultipartFile f) {
		Boolean res = false;
		
		if(
				f.getContentType().equals("image/jpeg")		||	// .jpeg .jpg	JPEG images
				f.getContentType().equals("image/png")		||	// .png			Portable Network Graphics
				f.getContentType().equals("image/gif")		||	// .gif			Graphics Interchange Format (GIF)				
				f.getContentType().equals("image/tiff")		||	// .tif .tiff	Tagged Image File Format (TIFF)
				f.getContentType().equals("image/webp")			// .webp		WEBP image				
			)		
			res = true;
		
		return res;
	}
    
    private String obtenerExtension (MultipartFile file) {
    	return FilenameUtils.getExtension(file.getOriginalFilename());
    }

}