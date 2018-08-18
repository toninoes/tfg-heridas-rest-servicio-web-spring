package rha.service;

import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import rha.util.Fichero;


@Service
public class ImagenService {

	@Autowired
    private AlmacenamientoImgService almacenamientoImgService;
	
	@Autowired
	private ImagenRepository imagenRepository;
	
	@Autowired
	private CuraRepository curaRepository;

    @ResponseBody
    public ResponseEntity<Resource> descargarById(long id) {
    	Imagen imagen = imagenRepository.findById(id)
    			.orElseThrow(() -> new RecursoNoEncontradoException("Imagen", "id", id));
    	
        Resource file = almacenamientoImgService.loadAsResource(imagen.getNombre());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(file, httpHeaders, HttpStatus.OK);
    }
        
    @ResponseBody
    public ResponseEntity<Resource> descargarByNombre(String nombre) {
    	Imagen imagen = imagenRepository.findByNombre(nombre)
    			.orElseThrow(() -> new RecursoNoEncontradoException("Imagen", "nombre", nombre));
    	
        Resource file = almacenamientoImgService.loadAsResource(imagen.getNombre());
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(file, httpHeaders, HttpStatus.OK);
    }
    
    public ResponseEntity<?> subir(MultipartFile img, long curaId, String descripcion) {
    	if(Fichero.esImagen(img)) {
			Cura cura = curaRepository.findById(curaId)
		            .orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", curaId));
			
			descripcion = eliminarDoblesComillas(descripcion);
    		Imagen imagen = new Imagen(img.getOriginalFilename(), cura, descripcion);
    	
			imagenRepository.save(imagen);
    		
        	almacenamientoImgService.store(img);
        	
        	return new ResponseEntity<Imagen>(imagen, HttpStatus.CREATED);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    	} 
    }
    
    public ResponseEntity<?> subirsinCura(MultipartFile img)  {
    	if(Fichero.esImagen(img)) {
    		
    		Imagen imagen = new Imagen(img.getOriginalFilename());
    		imagenRepository.save(imagen);
    		String nuevoNombre = imagen.getId() + "." + obtenerExtension(img);
    		imagen.setNombre(nuevoNombre);    	
			imagenRepository.save(imagen);    		
        	almacenamientoImgService.store(img, nuevoNombre);
        	
        	return new ResponseEntity<Imagen>(imagen, HttpStatus.CREATED);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    	}
    }
    
    public void borrar(@PathVariable String filename) {
        almacenamientoImgService.delete(filename);
    }
    

    @ExceptionHandler(AlmacenamientoFicheroNoEncontradoException.class)
    public ResponseEntity<?> handleStorageFileNotFound(AlmacenamientoFicheroNoEncontradoException exc) {
        return ResponseEntity.notFound().build();
    }
    
    private String obtenerExtension (MultipartFile file) {
    	return FilenameUtils.getExtension(file.getOriginalFilename());
    }
    
    private String eliminarDoblesComillas(String string) {
    	if (string.length() >= 2 && string.charAt(0) == '"' && string.charAt(string.length() - 1) == '"')
    	{
    	    string = string.substring(1, string.length() - 1);
    	}
    	
    	return string;
    }

	public List<Imagen> findByCuraId(long id) {
		Cura cura = curaRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Cura", "id", id));
		
		return imagenRepository.findByCuraOrderByIdDesc(cura);
	}
}