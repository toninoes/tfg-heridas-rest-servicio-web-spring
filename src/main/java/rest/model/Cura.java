package rest.model;

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
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "curas")
@JsonIgnoreProperties(value = {"creacion", "actualizacion"}, allowGetters = true)
public class Cura  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String evolucion;
    
    @NotBlank
    private String tratamiento;
    
    private String recomendaciones;
    
    @Column(unique = true)
    private String foto;
    
    @ManyToOne
	@JoinColumn(name="proceso_id", nullable = false)
	private Proceso proceso;
   
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creacion;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date actualizacion;

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

	public long getId() {
		return id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}    

}
