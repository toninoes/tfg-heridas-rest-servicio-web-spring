package rha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import rha.exception.AlmacenamientoFicheroNoEncontradoException;

@Service
public class ImagenService {

	@Autowired
    private AlmacenamientoService almacenamientoService;
    
    @ResponseBody
    public ResponseEntity<Resource> descargar(@PathVariable String filename) {
        Resource file = almacenamientoService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    
    public void borrar(@PathVariable String filename) {
        almacenamientoService.delete(filename);
    }
    
    public String subir(MultipartFile file) {
        almacenamientoService.store(file);        
        return file.getOriginalFilename();
    }

    @ExceptionHandler(AlmacenamientoFicheroNoEncontradoException.class)
    public ResponseEntity<?> handleStorageFileNotFound(AlmacenamientoFicheroNoEncontradoException exc) {
        return ResponseEntity.notFound().build();
    }

}