package rha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import rha.jwt.model.security.ActivacionUsuario;
import rha.util.Fecha;
import rha.util.Mail;

@Service
public class EmailService {

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
    private String remitente;
	
	@Value("${protocolo}")
	private String prot;
	
	@Value("${dominio}")
    private String url;
	
	@Value("${puerto}")
    private String port;
	
	@Value("${servicio}")
    private String servicio;
	
	@Value("${jwt.expiration}")
    private Long expiration;
	
	
	private String getBaseUrl() {
		String baseUrl;
		if(port.isEmpty() && servicio.isEmpty())
            baseUrl = String.format("%s://%s/", prot, url);
        else if(port.isEmpty() && !servicio.isEmpty())
            baseUrl = String.format("%s://%s/%s/", prot, url, servicio);
        else if(!port.isEmpty() && servicio.isEmpty())
            baseUrl = String.format("%s://%s:%s/", prot, url, port);
        else
            baseUrl = String.format("%s://%s:%s/%s/", prot, url, port, servicio);
		
		return baseUrl;
	}

    public void enviarEmailPersonalizado(final Mail mail){
        SimpleMailMessage correo = new SimpleMailMessage();
        correo.setSubject(mail.getAsunto());
        correo.setText(mail.getContenido());
        correo.setTo(mail.getPara());
        correo.setFrom(mail.getDe());

        javaMailSender.send(correo);
    }

	public void enviarCorreoActivacion(ActivacionUsuario activacionUsuario) {
		SimpleMailMessage correo = new SimpleMailMessage();
		correo.setSubject("Activación de usuario");
		
		String contenido = "Estimado " + activacionUsuario.getUser().getFullName() + "\n\n";
		contenido += "Para finalizar su registro y definir su contraseña es necesario que active "
				+ "su cuenta de usuario, siguiendo el siguiente enlace o copiándolo en la barra "
				+ "de direcciones de su navegador.\n\n";
		contenido += getBaseUrl();
		contenido += "activacion/" + activacionUsuario.getTokenActivacion() + "\n\n";
		contenido += "Tiene hasta el " + Fecha.fechaHoraSP(activacionUsuario.getFechaExpiracion()) +
				" para activar su cuenta"; 
		
		correo.setText(contenido);
		correo.setTo(activacionUsuario.getUser().getEmail());
        correo.setFrom(remitente);
		
		javaMailSender.send(correo);		
	}

	public void enviarCorreoResetPassword(ActivacionUsuario activacionUsuario) {
		SimpleMailMessage correo = new SimpleMailMessage();
		correo.setSubject("Cambio de contraseña");
		
		String contenido = "Estimado " + activacionUsuario.getUser().getFullName() + "\n\n";
		contenido += "Alguien ha solicitado recientemente un cambio de contraseña para su cuenta de "
				+ "RestHeridApp. Si ha sido usted, puede definir una contraseña nueva aquí: \n\n";
		contenido += getBaseUrl();
		contenido += "resetpassword/" + activacionUsuario.getTokenActivacion() + "\n\n";
		contenido += "Si no quiere cambiar su contraseña o no ha realizado usted esta solicitud, haga "
				+ "caso omiso de este mensaje y bórrelo.\n\n";
		contenido += "Para mantener a salvo su cuenta, le rogamos que no reenvíe este "
				+ "mensaje a nadie.";
		
		correo.setText(contenido);
		correo.setTo(activacionUsuario.getUser().getEmail());
        correo.setFrom(remitente);
		
		javaMailSender.send(correo);
	}
    
    
}
