package rha.jwt.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rha.jwt.security.JwtTokenUtil;
import rha.jwt.security.JwtUser;


@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
        
    @GetMapping("ping")
    public ResponseEntity<?> ping() {
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 

    @GetMapping("user")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }   
    /*
    @GetMapping("activacion/{activacionId}")
    public String activacionUsuario(@PathVariable String activacionId) {
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
        		userRepository.save(user);
        		resultado = "Bienvenido " + user.getFirstname() + ", su cuenta ha quedado activada correctamente.";
        	}
    	}

    	return resultado;
    }
    */
}
