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
@Table(name = "imagenes")
@JsonIgnoreProperties(value = {"creacion"}, allowGetters = true)
public class Imagen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String nombre;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="cura_id", nullable = false)
	private Cura cura;

	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creacion;

	public Imagen() {
		super();
	}

	public Imagen(@NotBlank String nombre) {
		super();
		this.nombre = nombre;
	}

	public Imagen(@NotBlank String nombre, Cura cura) {
		super();
		this.nombre = nombre;
		this.cura = cura;
	}
	
	public Imagen(@NotBlank String nombre, Cura cura, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cura = cura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Cura getCura() {
		return cura;
	}

	public void setCura(Cura cura) {
		this.cura = cura;
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
