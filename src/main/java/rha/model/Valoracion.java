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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "valoraciones")
@JsonIgnoreProperties(value = {"fecha"}, allowGetters = true)
public class Valoracion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="sanitario_id", nullable = false)
	private Sanitario sanitario;
	
	@NotNull
	@Min(0)
	@Max(10)
	private Double nota;
	
	@Column(length = 280) //como un tweet
	private String observaciones;

	public Valoracion() {
		super();
	}

	public Valoracion(Sanitario sanitario, @NotNull @Min(0) @Max(10) Double nota, String observaciones) {
		super();
		this.sanitario = sanitario;
		this.nota = nota;
		this.observaciones = observaciones;
	}

	public Sanitario getSanitario() {
		return sanitario;
	}

	public void setSanitario(Sanitario sanitario) {
		this.sanitario = sanitario;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public long getId() {
		return id;
	}

	public Date getFecha() {
		return fecha;
	}
	
	

}
