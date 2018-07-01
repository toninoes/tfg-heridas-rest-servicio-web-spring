package rha.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "procedimientos")
public class Procedimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Introduzca el código CPM para el procedimiento.")
	private String codigo;
	
	@Column(unique = true)
	@NotBlank(message = "Introduzca el nombre del procedimiento.")
	private String nombre;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "procedimientos")
	private Set<Grupodiagnostico> gruposdiagnostico;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "procedimientos")
	private Set<Proceso> procesos;

	public Procedimiento() {
		super();
	}

	public Procedimiento(@NotBlank(message = "Introduzca el código CPM para el procedimiento.") String codigo,
			@NotBlank(message = "Introduzca el nombre del procedimiento.") String nombre,
			Set<Grupodiagnostico> gruposdiagnostico) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.gruposdiagnostico = gruposdiagnostico;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	

	public Set<Grupodiagnostico> getGruposdiagnostico() {
		return gruposdiagnostico;
	}

	public void setGruposdiagnostico(Set<Grupodiagnostico> gruposdiagnostico) {
		this.gruposdiagnostico = gruposdiagnostico;
	}

	public long getId() {
		return id;
	}
	
	
}
