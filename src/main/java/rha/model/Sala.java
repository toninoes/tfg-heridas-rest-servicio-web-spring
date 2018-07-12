package rha.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "salas")
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Introduzca el nombre de la sala.")
	@Size(min = 3, max = 50, message = "el tamaño tiene que estar entre 3 y 50")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="centro_id", nullable = false)
	private Centro centro;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sala")
    private Set<Cita> citas = new HashSet<Cita>();
	
	public Sala() {
		super();
	}

	public Sala(@NotBlank(message = "Introduzca el nombre de la sala.") String nombre, Centro centro) {
		super();
		this.nombre = nombre;
		this.centro = centro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public long getId() {
		return id;
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
