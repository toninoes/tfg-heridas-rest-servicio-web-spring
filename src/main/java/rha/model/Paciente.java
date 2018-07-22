package rha.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import rha.jwt.model.security.Authority;
import rha.jwt.model.security.User;

@Entity
@Table(name = "pacientes")
@DiscriminatorValue("pa")
public class Paciente extends User  {
	
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
			@NotNull Boolean enabled, ArrayList<Boolean> permisos, @NotNull Date nacimiento, @NotBlank String dni) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.enabled = enabled;
		this.permisos = permisos;
		this.nacimiento = nacimiento;
		this.dni = dni;
	}
	
	public Paciente(@NotNull @Size(min = 4, max = 50) String username,
			@NotNull @Size(min = 4, max = 100) String password, @NotNull @Size(min = 4, max = 50) String firstname,
			@NotNull @Size(min = 4, max = 50) String lastname, @NotNull @Size(min = 4, max = 50) String email,
			@NotNull Boolean enabled, List<Authority> authorities, @NotNull Date nacimiento, @NotBlank String dni,
			Long historia) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.enabled = enabled;
		this.authorities = authorities;
		this.nacimiento = nacimiento;
		this.dni = dni;
		this.historia = historia;
	}
    
    //Getters y Setters
	
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
