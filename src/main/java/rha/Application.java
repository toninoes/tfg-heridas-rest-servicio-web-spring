package rha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringVersion;
import rha.jwt.security.repository.UserRepository;
import rha.repository.PacienteRepository;
import rha.service.EmailService;
import rha.util.almacenamiento.config.AlmacenamientoImgConfig;
import rha.util.almacenamiento.service.AlmacenamientoImgService;

@SpringBootApplication
@EnableCaching
@Configuration
@EnableConfigurationProperties({AlmacenamientoImgConfig.class})
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Spring Version: " + SpringVersion.getVersion());
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	EmailService es;
	@Bean
    CommandLineRunner init(AlmacenamientoImgService almacenamientoService, 
    		UserRepository userRepository, PacienteRepository pacienteRepository) {
        return (args) -> {
            almacenamientoService.init();
        };
    }

	
}
