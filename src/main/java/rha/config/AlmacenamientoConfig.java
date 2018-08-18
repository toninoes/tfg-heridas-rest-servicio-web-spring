package rha.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class AlmacenamientoConfig {

    /**
     * Localización de los ficheros
     */
    private final String LOCATION = "../RESTheridAppStorage";
    
    /**
     * Tamaño máximo ficheros
     */
    private final long TAM_MAX_FICHERO = 7340032; // 7 MiB (7340032 B)

    public String getLocation() {
        return LOCATION;
    }

	public long getTamMaxFichero() {
		return TAM_MAX_FICHERO;
	}

}
