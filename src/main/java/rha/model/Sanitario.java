package rha.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name = "sanitarios")
@DiscriminatorValue("sa")
public class Sanitario extends User {
	
	@JsonIgnore
	@OneToMany(mappedBy = "sanitario")
    private List<Cura> curas;

    public Sanitario() {
		super();
	}
    
    public Sanitario(@NotNull @Size(min = 4, max = 50) String username,
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
	
	public Sanitario(@NotNull @Size(min = 4, max = 50) String username,
			@NotNull @Size(min = 4, max = 100) String password, @NotNull @Size(min = 4, max = 50) String firstname,
			@NotNull @Size(min = 4, max = 50) String lastname, @NotNull @Size(min = 4, max = 50) String email,
			@NotNull Boolean enabled, List<Authority> authorities, @NotNull Date nacimiento, @NotBlank String dni,
			String colegiado) {
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
		this.colegiado = colegiado;
	}
    
	// Getters y Setters
	
	public List<Cura> getCuras() {
		return curas;
	}

	public void setCuras(List<Cura> curas) {
		this.curas = curas;
	}


}
