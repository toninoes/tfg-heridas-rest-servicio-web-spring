package rha.util.almacenamiento.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rha.exception.AlmacenamientoException;
import rha.exception.AlmacenamientoFicheroNoEncontradoException;
import rha.util.almacenamiento.config.AlmacenamientoDocConfig;

import java.nio.file.Path;
import java.util.stream.Stream;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;

@Service
public class AlmacenamientoDocService {
	private Path rootLocationDOC;
	
	private long tam_max_fichero;
	private double MibToB = 1048576; //1024 x 1024

    @Autowired
    public AlmacenamientoDocService(AlmacenamientoDocConfig properties) {
        this.rootLocationDOC = Paths.get(properties.getLocationDOC());
        this.tam_max_fichero = properties.getTamMaxFichero();
    }

    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new AlmacenamientoException("Error al guardar el fichero vacío " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new AlmacenamientoException(
                        "No se puede almacenar el fichero con una ruta relativa fuera del directorio actual "
                                + filename);
            }
            
            Files.copy(file.getInputStream(), this.rootLocationDOC.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            
        }
        catch (IOException e) {
            throw new AlmacenamientoException("Error al almacenar el fichero " + filename, e);
        }
    }
    
    public void store(MultipartFile file, String name) {
        String filename = StringUtils.cleanPath(name);
        try {
            if (file.isEmpty()) {
                throw new AlmacenamientoException("Error al guardar el fichero vacío " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new AlmacenamientoException(
                        "No se puede almacenar el fichero con una ruta relativa fuera del directorio actual "
                                + filename);
            }
            
            if (file.getSize() > tam_max_fichero) {
            	throw new AlmacenamientoException(String.format("ERROR: El fichero supera los %.2f MiB", 
            			(tam_max_fichero/MibToB)));
            }
            
            Files.copy(file.getInputStream(), this.rootLocationDOC.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            
        }
        catch (IOException e) {
            throw new AlmacenamientoException("Error al almacenar el fichero " + filename, e);
        }
    }

    
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocationDOC, 1)
                    .filter(path -> !path.equals(this.rootLocationDOC))
                    .map(path -> this.rootLocationDOC.relativize(path));
        }
        catch (IOException e) {
            throw new AlmacenamientoException("Error al leer los ficheros almacenados", e);
        }

    }
    

    public Path load(String filename) {
        return rootLocationDOC.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new AlmacenamientoFicheroNoEncontradoException(
                        "No se pudo leer el fichero: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new AlmacenamientoFicheroNoEncontradoException("No se pudo leer el fichero: " + filename, e);
        }
    }

    public void delete(String filename) {
        try {
			FileSystemUtils.deleteRecursively(rootLocationDOC.resolve(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocationDOC.toFile());
    }

    public void init() {
        try {
            Files.createDirectories(rootLocationDOC);
        }
        catch (IOException e) {
            throw new AlmacenamientoException("No se pudo inicializar el almacenamiento", e);
        }
    }

}
