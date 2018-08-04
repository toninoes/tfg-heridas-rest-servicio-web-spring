package rha.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "curas")
@JsonIgnoreProperties(value = {"creacion", "actualizacion"}, allowGetters = true)
public class Cura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String evolucion;
	
	@NotBlank(message = "Introduzca un tratamiento")
    private String tratamiento;
    private String recomendaciones;
      
    @ManyToOne
	@JoinColumn(name="proceso_id", nullable = false)
	private Proceso proceso;
    
    @ManyToOne
	@JoinColumn(name="sanitario_id", nullable = false)
	private Sanitario sanitario;
    
	@JsonIgnore
	@OneToMany(mappedBy = "cura")
    private List<Imagen> imagenes;
	
	private Boolean valorada = false;
   
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creacion;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @JsonIgnore
    private Date actualizacion;

	public Cura() {
		super();
	}
	
	public Cura(String evolucion, @NotBlank String tratamiento, String recomendaciones, Proceso proceso) {
		super();
		this.evolucion = evolucion;
		this.tratamiento = tratamiento;
		this.recomendaciones = recomendaciones;
		this.proceso = proceso;
	}

	public Cura(String tratamiento, Proceso proceso, Sanitario sanitario) {
		super();
		this.tratamiento = tratamiento;
		this.proceso = proceso;
		this.sanitario = sanitario;
	}

	public String getEvolucion() {
		return evolucion;
	}

	public void setEvolucion(String evolucion) {
		this.evolucion = evolucion;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getRecomendaciones() {
		return recomendaciones;
	}

	public void setRecomendaciones(String recomendaciones) {
		this.recomendaciones = recomendaciones;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
	
	public Sanitario getSanitario() {
		return sanitario;
	}

	public void setSanitario(Sanitario sanitario) {
		this.sanitario = sanitario;
	}

	public long getId() {
		return id;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}
	
	public Boolean estaValorada() {
		return valorada;
	}

	public void setValorada(Boolean valorada) {
		this.valorada = valorada;
	}

	public Date getCreacion() {
		return creacion;
	}

	public Date getActualizacion() {
		return actualizacion;
	} 
	
	

}
