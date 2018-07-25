package rha.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import rha.jwt.model.security.User;

/**
 * Solución 1 de 
 * http://www.codejava.net/frameworks/hibernate/hibernate-many-to-many-association-with-extra-columns-in-join-table-example
 */
@Entity
public class UserCentro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID") 
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CENTRO_ID")
	private Centro centro;
	
	// campos adicionales (atributos de enlace)
	
	@NotNull(message = "Introduzca una fecha de inicio")
    private Date inicio;
    
    private Date fin;

	public UserCentro() {
		super();
	}

	public UserCentro(User user, Centro centro, @NotNull(message = "Introduzca una fecha de inicio") Date inicio) {
		super();
		this.user = user;
		this.centro = centro;
		this.inicio = inicio;
	}

	public long getId() {
		return id;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}
    
    
}
