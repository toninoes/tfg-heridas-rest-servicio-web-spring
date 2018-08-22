package rha.jwt.security.controller;

import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rha.jwt.model.security.ActivacionUsuario;
import rha.jwt.model.security.CambiarPassword;
import rha.jwt.model.security.User;
import rha.jwt.security.repository.ActivacionUsuarioRepository;
import rha.jwt.security.repository.UserRepository;

@Controller
public class WebMetodos {
	
	@Autowired
	private ActivacionUsuarioRepository actUsrRep;
	
	@Autowired
	private UserRepository usrRep;
		
	@Autowired
	private PasswordEncoder pass;
	
	@GetMapping("activacion/{activacionId}")
    public String activacionUsuario(Model model, @PathVariable String activacionId) {
    	String resultado = null;
    	
    	ActivacionUsuario activacionUsuario = actUsrRep.findByTokenActivacion(activacionId);
    	if(activacionUsuario == null) {
    		resultado = "Token de activación inválido.";
    	} else {
    		User user = activacionUsuario.getUser();
    		Calendar cal = Calendar.getInstance();
        	
        	if((activacionUsuario.getFechaExpiracion().getTime() - cal.getTime().getTime()) <= 0) {
        		resultado = "Ha pasado el tiempo para activar su cuenta. Contacte con su administrador.";
        		actUsrRep.delete(activacionUsuario);
        	} else {
        		actUsrRep.delete(activacionUsuario);
        		user.setEnabled(true);
        		usrRep.save(user);
        		resultado = "Bienvenido " + user.getFirstname() + ", su cuenta ha quedado activada correctamente.";
        	}
    	}
    	
    	model.addAttribute("resultado", resultado);

    	return "resultados";
    }
    	
		
	@GetMapping("resetpassword/{activacionId}")
	public String resetpasswordShowForm(Model model, @PathVariable String activacionId) {
		String resultado = null;
		String email = null;
		
		ActivacionUsuario activacionUsuario = actUsrRep.findByTokenActivacion(activacionId);
    	if(activacionUsuario == null) {
    		resultado = "Token inválido. No es posible cambiar su contraseña";
    	} else {
    		User user = activacionUsuario.getUser();
    		Calendar cal = Calendar.getInstance();
        	
        	if((activacionUsuario.getFechaExpiracion().getTime() - cal.getTime().getTime()) <= 0) {
        		resultado = "Ha pasado el tiempo para cambiar su contraseña.";
        		actUsrRep.delete(activacionUsuario);
        	} else {
        		email = activacionUsuario.getUser().getEmail();
        		user.setEnabled(true);
        		usrRep.save(user);
        	}
    	}		
    			
		model.addAttribute("resultado", resultado);
		model.addAttribute("cambiarpassword", new CambiarPassword());
		model.addAttribute("activacionId", activacionId);
		model.addAttribute("email", email);
		
		if(email == null)
			return "resultados";
		else		
			return "resetpasswordform";
	}
	
	@PostMapping("resetpassword/{activacionId}")
	public String resetpasswordProcessForm(Model model, @PathVariable String activacionId, 
			@ModelAttribute CambiarPassword cp) {
		
		String resultado = null;
		if(cp.getPassword().equals(cp.getPassword_nueva()) && (cp.getPassword().length() >= 4)) {
							
			ActivacionUsuario activacionUsuario = actUsrRep.findByTokenActivacion(activacionId);
	    	if(activacionUsuario == null) {
	    		resultado = "Token inválido. No es posible cambiar su contraseña";
	    	} else {
	    		User user = activacionUsuario.getUser();
	    		Calendar cal = Calendar.getInstance();
	        	
	        	if((activacionUsuario.getFechaExpiracion().getTime() - cal.getTime().getTime()) <= 0) {
	        		resultado = "Ha pasado el tiempo para cambiar su contraseña.";
	        		actUsrRep.delete(activacionUsuario);
	        	} else {
	        		actUsrRep.delete(activacionUsuario);
	        		user.setEnabled(true);
	        		// establece nueva password encriptándola en bbdd
	        		user.setPassword(pass.encode(cp.getPassword_nueva()));
	        		usrRep.save(user);
	        		resultado = "Perfecto " + user.getFirstname() + ", su contraseña se ha establecido correctamente.";
	        	}
	    	}
	    	
	    	model.addAttribute("resultado", resultado);
	    	return "resultados";
	    	
		} else if (!cp.getPassword().equals(cp.getPassword_nueva())) {
			
			resultado = "Debes introducir dos veces la misma contraseña.";
		} else {
			resultado = "El tamaño de la contraseña tiene que estar entre 4 y 100.";	
		}
			
		model.addAttribute("resultado", resultado);
		model.addAttribute("cambiarpassword", new CambiarPassword());
		model.addAttribute("activacionId", activacionId);
		model.addAttribute("email", cp.getEmail());
		
		return "resetpasswordform";
		
    	
	}
	
	

	
	
	
	
	

}