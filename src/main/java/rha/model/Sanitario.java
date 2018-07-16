package rha.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import rha.jwt.model.security.Authority;
import rha.jwt.model.security.User;


@Entity
@Table(name = "sanitarios")
@JsonIgnoreProperties(value = {"creacion", "actualizacion"}, allowGetters = true)
public class Sanitario extends User {
	
	@Column(unique = true, length = 9)
	@NotBlank(message = "Introduzca el DNI.")
	private String dni;
	
	@NotNull(message = "Introduzca una fecha de nacimiento")
	private Date nacimiento;
	
	@Column(unique = true)
	@NotNull(message = "Introduzca el número de colegiado.")
	private Long colegiado;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sanitario")
    private List<Cura> curas;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonIgnore
    private Date creacion;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @JsonIgnore
    private Date actualizacion;
    
    public Sanitario() {
		super();
	}
    
    public Sanitario(@NotNull @Size(min = 4, max = 50) String username,
			@NotNull @Size(min = 4, max = 100) String password, @NotNull @Size(min = 4, max = 50) String firstname,
			@NotNull @Size(min = 4, max = 50) String lastname, @NotNull @Size(min = 4, max = 50) String email,
			List<Authority> authorities, @NotNull Date nacimiento, @NotBlank String dni, @NotNull Long colegiado) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.authorities = authorities;
		this.nacimiento = nacimiento;
		this.dni = dni;
		this.colegiado = colegiado;
	}
	
	public Sanitario(@NotNull @Size(min = 4, max = 50) String username,
			@NotNull @Size(min = 4, max = 100) String password, @NotNull @Size(min = 4, max = 50) String firstname,
			@NotNull @Size(min = 4, max = 50) String lastname, @NotNull @Size(min = 4, max = 50) String email,
			@NotNull Boolean enabled, @NotNull Date lastPasswordResetDate, List<Authority> authorities, 
			@NotNull Date nacimiento, @NotBlank String dni, @NotNull Long colegiado) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
		this.nacimiento = nacimiento;
		this.dni = dni;
		this.colegiado = colegiado;
	}
    
    public Sanitario(@NotNull @Size(min = 4, max = 50) String username, @NotNull @Size(min = 4, max = 100) String password,
			@NotNull @Size(min = 4, max = 50) String firstname, @NotNull @Size(min = 4, max = 50) String lastname,
			@NotNull @Size(min = 4, max = 50) String email, @NotNull Boolean enabled,
			@NotNull Date lastPasswordResetDate, List<Authority> authorities, ArrayList<Boolean> permisos,
			@NotNull Date nacimiento, @NotBlank String dni, @NotNull Long colegiado) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
		this.permisos = permisos;
		this.nacimiento = nacimiento;
		this.dni = dni;
		this.colegiado = colegiado;
	}
    
	// Getters y Setters

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public Long getColegiado() {
		return colegiado;
	}

	public void setColegiado(Long colegiado) {
		this.colegiado = colegiado;
	}
	
	public List<Cura> getCuras() {
		return curas;
	}

	public void setCuras(List<Cura> curas) {
		this.curas = curas;
	}

    public Date getCreacion() {
		return creacion;
	}
    
	public Date getActualizacion() {
		return actualizacion;
	}

}