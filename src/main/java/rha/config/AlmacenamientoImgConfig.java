package rha.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class AlmacenamientoImgConfig {

    /**
     * Localización de los ficheros
     */
    private final String LOCATION_IMG = "../RESTheridAppIMG";
    
    /**
     * Tamaño máximo ficheros
     */
    private final long TAM_MAX_FICHERO = 7340032; // 7 MiB (7340032 B)

    public String getLocationIMG() {
        return LOCATION_IMG;
    }

	public long getTamMaxFichero() {
		return TAM_MAX_FICHERO;
	}

}
