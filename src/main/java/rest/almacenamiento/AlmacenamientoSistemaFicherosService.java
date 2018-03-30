package rest.almacenamiento;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import rest.exception.AlmacenamientoException;
import rest.exception.AlmacenamientoFicheroNoEncontradoException;

@Service
public class AlmacenamientoSistemaFicherosService implements AlmacenamientoService {

    private final Path rootLocation;

    @Autowired
    public AlmacenamientoSistemaFicherosService(AlmacenamientoProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
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
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new AlmacenamientoException("Error al almacenar el fichero " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        }
        catch (IOException e) {
            throw new AlmacenamientoException("Error al leer los ficheros almacenados", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
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

    @Override
    public void delete(String filename) {
        try {
			FileSystemUtils.deleteRecursively(rootLocation.resolve(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new AlmacenamientoException("No se pudo inicializar el almacenamiento", e);
        }
    }
}
