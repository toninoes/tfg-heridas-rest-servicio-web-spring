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
@Table(name = "cuidados")
public class Cuidado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	@NotBlank(message = "Introduzca el nombre del cuidado.")
	private String nombre;
	
	@NotBlank(message = "Introduzca la descripción del cuidado.")
	private String descripcion;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "cuidados")
	private Set<Proceso> procesos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Proceso> getProcesos() {
		return procesos;
	}

	public void setProcesos(Set<Proceso> procesos) {
		this.procesos = procesos;
	}

	public long getId() {
		return id;
	}
	
	
	
	
}

