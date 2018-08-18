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
	
	@NotBlank
	private String nombre;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="diagnostico_id", nullable = false)
	private Diagnostico diagnostico;

	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creacion;

	public Cuidado() {
		super();
	}

	public Cuidado(@NotBlank String nombre) {
		super();
		this.nombre = nombre;
	}

	public Cuidado(@NotBlank String nombre, Diagnostico diagnostico) {
		super();
		this.nombre = nombre;
		this.diagnostico = diagnostico;
	}
	
	public Cuidado(@NotBlank String nombre, Diagnostico diagnostico, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.diagnostico = diagnostico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
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
	

}
