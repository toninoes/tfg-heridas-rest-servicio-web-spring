package rha.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class ImagenConfig {

    /**
     * Localización de las imágenes
     */
    private final String LOCATION = "../RESTheridAppIMG";
    
    /**
     * Tamaño máximo imágenes
     */
    private final long TAM_MAX_IMAGEN = 7340032; // 7 MiB (7340032 B)

    public String getLocation() {
        return LOCATION;
    }

	public long getTamMaxImagen() {
		return TAM_MAX_IMAGEN;
	}

}
