package rha.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import rha.jwt.model.security.Authority;
import rha.jwt.model.security.User;

@Entity
@Table(name = "pacientes")
@DiscriminatorValue("pa")
public class Paciente extends User  {

	@Column(unique = true, length = 9)
	private String dni;
	
	//@NotNull(message = "Introduzca una fecha de nacimiento")
	private Date nacimiento;

	@Column(unique = true)
	private Long historia;
	
	@JsonIgnore
	@OneToMany(mappedBy = "paciente")
    private List<Proceso> procesos;
	
	@JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private Set<Cita> citas = new HashSet<Cita>();
	
   
    public Paciente() {
		super();
	}
    
    public Paciente(@NotNull @Size(min = 4, max = 50) String username,
			@NotNull @Size(min = 4, max = 100) String password, @NotNull @Size(min = 4, max = 50) String firstname,
			@NotNull @Size(min = 4, max = 50) String lastname, @NotNull @Size(min = 4, max = 50) String email,
			List<Authority> authorities, @NotNull Date nacimiento) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.authorities = authorities;
		this.nacimiento = nacimiento;
	}
    
    public Paciente(@NotNull @Size(min = 4, max = 50) String username,
			@NotNull @Size(min = 4, max = 100) String password, @NotNull @Size(min = 4, max = 50) String firstname,
			@NotNull @Size(min = 4, max = 50) String lastname, @NotNull @Size(min = 4, max = 50) String email,
			@NotNull Boolean enabled, @NotNull Date lastPasswordResetDate, List<Authority> authorities, 
			@NotNull Date nacimiento) {
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
	}

	public Paciente(@NotNull @Size(min = 4, max = 50) String username,
			@NotNull @Size(min = 4, max = 100) String password, @NotNull @Size(min = 4, max = 50) String firstname,
			@NotNull @Size(min = 4, max = 50) String lastname, @NotNull @Size(min = 4, max = 50) String email,
			@NotNull Boolean enabled, @NotNull Date lastPasswordResetDate, List<Authority> authorities, 
			@NotNull Date nacimiento, String dni) {
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
	}
    
    public Paciente(@NotNull @Size(min = 4, max = 50) String username, @NotNull @Size(min = 4, max = 100) String password,
			@NotNull @Size(min = 4, max = 50) String firstname, @NotNull @Size(min = 4, max = 50) String lastname,
			@NotNull @Size(min = 4, max = 50) String email, @NotNull Boolean enabled,
			@NotNull Date lastPasswordResetDate, List<Authority> authorities, ArrayList<Boolean> permisos,
			@NotNull Date nacimiento, String dni) {
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
	}
    
    //Getters y Setters
	
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

	public Long getHistoria() {
		return historia;
	}

	public void setHistoria(Long historia) {
		this.historia = historia;
	}

	public List<Proceso> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}

	public Set<Cita> getCitas() {
		return citas;
	}

	public void setCitas(Set<Cita> citas) {
		this.citas = citas;
	}
	
	public void addCita(Cita cita) {
        this.citas.add(cita);
    } 

}
