package rest.almacenamiento;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class AlmacenamientoProperties {

    /**
     * Localización de las imágenes
     */
    private String location = "src/main/resources/static/img";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
