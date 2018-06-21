package rha.jwt.model.security;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class ActivacionUsuario {
	// 1 día, en minutos
	private static final int EXPIRACION = 60 * 24;
	 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
    private String tokenActivacion;
   
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
     
    private Date fechaExpiracion;

    public ActivacionUsuario() {
		super();
		this.fechaExpiracion = calcularFechaExpiracion(EXPIRACION);
		this.tokenActivacion = UUID.randomUUID().toString();
	}

	public ActivacionUsuario(User user) {
		super();
		this.fechaExpiracion = calcularFechaExpiracion(EXPIRACION);
		this.tokenActivacion = UUID.randomUUID().toString();
		this.user = user;
	}

	private Date calcularFechaExpiracion(int tiempoExpiracionEnMinutos) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, tiempoExpiracionEnMinutos);
        return new Date(cal.getTime().getTime());
    }

	public String getTokenActivacion() {
		return tokenActivacion;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}	

}
