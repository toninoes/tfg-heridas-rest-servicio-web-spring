package rha.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "centros")
public class Centro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	@NotBlank(message = "Introduzca el nombre del centro sanitario.")
	private String nombre;
	
	@NotBlank(message = "Introduzca la dirección del centro.")
	private String direccion;
	
	private String telefono;
	
	@JsonIgnore
	@OneToMany(mappedBy = "centro")
    private Set<UserCentro> userCentros = new HashSet<UserCentro>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "centro")
    private List<Sala> salas;

	public Centro() {
		super();
	}

	public Centro(@NotBlank(message = "Introduzca el nombre del centro sanitario.") String nombre,
			@NotBlank(message = "Introduzca la dirección del centro.") String direccion, String telefono) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Centro(@NotBlank(message = "Introduzca el nombre del centro sanitario.") String nombre,
			@NotBlank(message = "Introduzca la dirección del centro.") String direccion) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public long getId() {
		return id;
	}
	
	public Set<UserCentro> getUserCentros() {
		return userCentros;
	}

	public void setUserCentros(Set<UserCentro> userCentros) {
		this.userCentros = userCentros;
	}
	
	public void addUserCentro(UserCentro userCentro) {
        this.userCentros.add(userCentro);
    }

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	} 
	
	

}
