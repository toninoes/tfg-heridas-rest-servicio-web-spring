package rest.almacenamiento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import rest.exception.AlmacenamientoFicheroNoEncontradoException;


public class Almacenamiento {

    private final AlmacenamientoService almacenamientoService;

    @Autowired
    public Almacenamiento(AlmacenamientoService almacenamientoService) {
        this.almacenamientoService = almacenamientoService;
    }
    
    public ResponseEntity<Resource> bajarFichero(@PathVariable String filename) {
        Resource file = almacenamientoService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    
    public void borrarFichero(@PathVariable String filename) {
        almacenamientoService.delete(filename);
    }
    
    public String subirFichero(MultipartFile file) {
        almacenamientoService.store(file);        
        return file.getOriginalFilename();
    }

    @ExceptionHandler(AlmacenamientoFicheroNoEncontradoException.class)
    public ResponseEntity<?> handleStorageFileNotFound(AlmacenamientoFicheroNoEncontradoException exc) {
        return ResponseEntity.notFound().build();
    }

}