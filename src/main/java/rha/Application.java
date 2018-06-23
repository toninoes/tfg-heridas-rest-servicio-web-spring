package rha;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import rha.config.ImagenConfig;
import rha.jwt.model.security.Authority;
import rha.jwt.model.security.AuthorityName;
import rha.jwt.model.security.User;
import rha.jwt.security.JwtUserFactory;
import rha.jwt.security.repository.AuthorityRepository;
import rha.jwt.security.repository.UserRepository;
import rha.model.Paciente;
import rha.model.Sanitario;
import rha.repository.PacienteRepository;
import rha.service.AlmacenamientoService;

@SpringBootApplication
@EnableConfigurationProperties(ImagenConfig.class)
public class Application extends SpringBootServletInitializer {
	
	@Autowired 
	private AuthorityRepository authRep;
	
	
	@Autowired
	private PasswordEncoder pass;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    CommandLineRunner init(AlmacenamientoService almacenamientoService, 
    		UserRepository userRepository, PacienteRepository pacienteRepository) {
        return (args) -> {
            almacenamientoService.init();
            
            if(userRepository.findAll().isEmpty()) {
        		Authority rolAdmin = new Authority(AuthorityName.ROLE_ADMIN);
        		Authority rolSanitario = new Authority(AuthorityName.ROLE_SANITARIO);
        		Authority rolPaciente = new Authority(AuthorityName.ROLE_PACIENTE);
        		
        		authRep.save(rolAdmin);
        		authRep.save(rolSanitario);
        		authRep.save(rolPaciente);

        		Paciente paciente = new Paciente("paciente@user.es", pass.encode("paciente"), "Paciente", "Paciente", 
        				"paciente@user.es", new ArrayList<Authority>(), new Date());
        		paciente.setEnabled(true);
        		paciente.addAuthority(rolPaciente);
        		pacienteRepository.save(paciente);
        		paciente.setHistoria(paciente.getId());
        		pacienteRepository.save(paciente);
        		JwtUserFactory.create(paciente);

        		Sanitario sanitario = new Sanitario("sanitario@user.es", pass.encode("sanitario"), "Sanitario", "Sanitario", 
        				"sanitario@user.es", new ArrayList<Authority>(), new Date(),
        				"52929190A", (long) 7699);
        		sanitario.setEnabled(true);
        		sanitario.addAuthority(rolSanitario);
        		userRepository.save(sanitario);
        		JwtUserFactory.create(sanitario);

        		User admin = new User("admin@user.es", pass.encode("admin"), "Admin", "Admin", 
        				"admin@user.es", new ArrayList<Authority>());
        		admin.setEnabled(true);
        		admin.addAuthority(rolAdmin);
        		userRepository.save(admin);
        		JwtUserFactory.create(admin);     		
        	}
        };
    }

}
