package rha.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class AlmacenamientoDocConfig {

    /**
     * Localización de los ficheros
     */
    private final String LOCATION_DOC = "../RESTheridAppDOC";
    
    /**
     * Tamaño máximo ficheros
     */
    private final long TAM_MAX_FICHERO = 7340032; // 7 MiB (7340032 B)

    public String getLocationDOC() {
        return LOCATION_DOC;
    }

	public long getTamMaxFichero() {
		return TAM_MAX_FICHERO;
	}

}
