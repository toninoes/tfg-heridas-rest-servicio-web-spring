package rha.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "cuidados")
@JsonIgnoreProperties(value = {"creacion"}, allowGetters = true)
public class Cuidado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Introduzca un nombre")
	private String nombre;
	
	@NotBlank(message = "Introduzca una descripción")
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="grupodiagnostico_id", nullable = false)
	private Grupodiagnostico grupodiagnostico;
	
	@ManyToOne
	@JoinColumn(name="sanitario_id", nullable = false)
	private Sanitario sanitario;

	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creacion;

	public Cuidado() {
		super();
	}
	
	public Cuidado(@NotBlank(message = "Introduzca un nombre") String nombre,
			@NotBlank(message = "Introduzca una descripción") String descripcion, Grupodiagnostico grupodiagnostico,
			Sanitario sanitario) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.grupodiagnostico = grupodiagnostico;
		this.sanitario = sanitario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Grupodiagnostico getGrupodiagnostico() {
		return grupodiagnostico;
	}

	public void setGrupodiagnostico(Grupodiagnostico grupodiagnostico) {
		this.grupodiagnostico = grupodiagnostico;
	}

	public long getId() {
		return id;
	}

	public Date getCreacion() {
		return creacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Sanitario getSanitario() {
		return sanitario;
	}

	public void setSanitario(Sanitario sanitario) {
		this.sanitario = sanitario;
	}
	

}
