package rha.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class ImagenLocationConfig {

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
