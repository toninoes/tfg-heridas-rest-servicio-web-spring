package rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import rest.almacenamiento.AlmacenamientoProperties;
import rest.almacenamiento.AlmacenamientoService;

@SpringBootApplication
@EnableConfigurationProperties(AlmacenamientoProperties.class)
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    CommandLineRunner init(AlmacenamientoService almacenamientoService) {
        return (args) -> {
            //storageService.deleteAll();
            almacenamientoService.init();
        };
    }

}
