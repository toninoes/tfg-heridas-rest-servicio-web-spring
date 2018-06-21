package rha.jwt.security.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rha.exception.CampoUnicoException;
import rha.exception.RegistroException;
import rha.jwt.model.security.ActivacionUsuario;
import rha.jwt.model.security.Authority;
import rha.jwt.model.security.AuthorityName;
import rha.jwt.model.security.User;
import rha.jwt.security.JwtUser;
import rha.jwt.security.JwtUserFactory;
import rha.jwt.security.repository.ActivacionUsuarioRepository;
import rha.jwt.security.repository.AuthorityRepository;
import rha.jwt.security.repository.UserRepository;
import rha.service.EmailService;

@RestController
public class MethodProtectedRestController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private AuthorityRepository authRep;
	
	@Autowired
	private ActivacionUsuarioRepository actUsrRep;
	
	@Autowired
	private UserRepository usrRep;
	
	@Autowired
	private PasswordEncoder pass;
	
	@Autowired
    private EmailService emailService;
    	
	@PostMapping("registro")
	@PreAuthorize("hasRole('ADMIN') or hasRole('SANITARIO')")
	public ResponseEntity<JwtUser> registro(@Valid @RequestBody User user) throws Exception {	
		try {
			return registrar(user);
		} catch (CampoUnicoException e) {
			throw new CampoUnicoException(e.getNombreRecurso(), e.getNombreCampo() , e.getValorCampo());
		} catch (RegistroException e) {
			throw new RegistroException(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
	}
	
	private ResponseEntity<JwtUser> registrar(User user) throws Exception {	
		// no se puede registrar a un usuario sin roles
		if(estaRegistrandoUserSinRoles(user.getPermisos()))
			throw new RegistroException("Debe asociar al menos un rol al nuevo usuario.");
			
		// los sanitarios (noAdmin) sólo pueden registrar pacientes.
		if(esSanitarioNoEsAdmin() && !estaRegistrandoSoloPaciente(user.getPermisos()))
			throw new RegistroException("Usted sólo puede registrar pacientes.");
		
		// control unicidad de email (y username...)
		if(usrRep.findByEmail(user.getEmail()).isPresent())
			throw new CampoUnicoException("Usuario", "email", user.getEmail());
		
		Authority rolAdmin = authRep.findByName(AuthorityName.ROLE_ADMIN);
		Authority rolSanitario = authRep.findByName(AuthorityName.ROLE_SANITARIO);
		Authority rolPaciente = authRep.findByName(AuthorityName.ROLE_PACIENTE);
		
		List<Authority> roles = new ArrayList<Authority>();

		if (user.getPermisos().get(0))
			roles.add(rolAdmin);
		if (user.getPermisos().get(1))
			roles.add(rolSanitario);
		if (user.getPermisos().get(2))
			roles.add(rolPaciente);
		
		User usuario = new User(user.getUsername(), pass.encode(user.getEmail()),
				user.getFirstname(), user.getLastname(), user.getEmail(),
				false, new Date(), roles);
		
		usrRep.save(usuario);
		
		ActivacionUsuario activacionUsuario = new ActivacionUsuario(usuario);
		actUsrRep.save(activacionUsuario);
		
		// Enviar aqui el mail de activacion.
		logger.info("Enviando correo de activación a '{}'", usuario.getEmail());
		emailService.enviarCorreoActivacion(activacionUsuario);
		
		return ResponseEntity.ok(JwtUserFactory.create(usuario));
	}
	

	/**
	 * Comprueba que sólo se está intentando registrar a un paciente.
	 * 
	 * @param permisos
	 * @return boolean
	 */
	private boolean estaRegistrandoSoloPaciente(ArrayList<Boolean> permisos) {		
		return (permisos.get(0) == false &&
				permisos.get(1) == false &&
				permisos.get(2) == true);
	}
	
	/**
	 * Comprueba que si está registrando a un usuario sin roles asociados.
	 * 
	 * @param permisos
	 * @return boolean
	 */
	private boolean estaRegistrandoUserSinRoles(ArrayList<Boolean> permisos) {		
		return (permisos.get(0) == false &&
				permisos.get(1) == false &&
				permisos.get(2) == false);
	}
	
	/**
	 * Comprueba si un sanitario es o no es administrador
	 * 
	 * @return boolean
	 */
	private boolean esSanitarioNoEsAdmin() {
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); 
		
		logger.info("Roles de '{}': '{}'", username, authorities);
		
		boolean esAdmin = false;
		boolean esSanitario = false;
		
		for(GrantedAuthority a: authorities) {
			if(a.getAuthority().equals(AuthorityName.ROLE_ADMIN.toString()))
				esAdmin = true;
			if(a.getAuthority().equals(AuthorityName.ROLE_SANITARIO.toString()))
				esSanitario = true;
		}
		
		logger.info("'{}'. esAdmin: '{}'. esSanitario: '{}'", username, esAdmin, esSanitario);
		
		return (esSanitario && !esAdmin);
	}
	
	
	
	
	
	

}
