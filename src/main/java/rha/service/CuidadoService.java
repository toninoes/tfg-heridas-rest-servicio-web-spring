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
import rha.model.Cuidado;
import rha.model.Diagnostico;
import rha.repository.CuidadoRepository;
import rha.repository.DiagnosticoRepository;


@Service
public class CuidadoService {

	@Autowired
    private AlmacenamientoService almacenamientoService;
	
	@Autowired
	private CuidadoRepository cuidadoRepository;
	
	@Autowired
	private DiagnosticoRepository diagnosticoRepository;
	

    @ResponseBody
    public ResponseEntity<Resource> descargarById(long id) {
    	Cuidado cuidado = cuidadoRepository.findById(id)
    			.orElseThrow(() -> new RecursoNoEncontradoException("Cuidado", "id", id));
    	
        Resource file = almacenamientoService.loadAsResource(cuidado.getNombre());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(file, httpHeaders, HttpStatus.OK);
    }
        
    @ResponseBody
    public ResponseEntity<Resource> descargarByNombre(String nombre) {
    	Cuidado cuidado = cuidadoRepository.findByNombre(nombre)
    			.orElseThrow(() -> new RecursoNoEncontradoException("Cuidado", "nombre", nombre));
    	
        Resource file = almacenamientoService.loadAsResource(cuidado.getNombre());
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(file, httpHeaders, HttpStatus.OK);
    }
    
    public ResponseEntity<?> subir(MultipartFile pdf, long diagnosticoId, String descripcion) {
    	if(esPDF(pdf)) {
			Diagnostico diagnostico = diagnosticoRepository.findById(diagnosticoId)
		            .orElseThrow(() -> new RecursoNoEncontradoException("Diagnostico", "id", diagnosticoId));
			
			descripcion = eliminarDoblesComillas(descripcion);
    		Cuidado cuidado = new Cuidado(pdf.getOriginalFilename(), diagnostico, descripcion);
    	
			cuidadoRepository.save(cuidado);
    		
        	almacenamientoService.store(pdf);
        	
        	return new ResponseEntity<Cuidado>(cuidado, HttpStatus.CREATED);
    	}else {
    		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    	} 
    }
    
    public ResponseEntity<?> subirsinDiagnostico(MultipartFile doc)  {
    	if(esPDF(doc)) {
    		
    		Cuidado cuidado = new Cuidado(doc.getOriginalFilename());
    		cuidadoRepository.save(cuidado);
    		String nuevoNombre = cuidado.getId() + "." + obtenerExtension(doc);
    		cuidado.setNombre(nuevoNombre);    	
			cuidadoRepository.save(cuidado);    		
        	almacenamientoService.store(doc, nuevoNombre);
        	
        	return new ResponseEntity<Cuidado>(cuidado, HttpStatus.CREATED);
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
    
    private Boolean esPDF(MultipartFile f) {
		return (f.getContentType().equals("application/pdf"));
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

	public List<Cuidado> findByDiagnosticoId(long id) {
		Diagnostico diagnostico = diagnosticoRepository.findById(id)
	            .orElseThrow(() -> new RecursoNoEncontradoException("Diagnostico", "id", id));
		
		return cuidadoRepository.findByDiagnosticoOrderByIdDesc(diagnostico);
	}
}