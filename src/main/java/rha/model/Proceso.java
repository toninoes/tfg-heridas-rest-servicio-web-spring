package rha.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "procesos")
@JsonIgnoreProperties(value = {"creacion", "actualizacion"}, allowGetters = true)
public class Proceso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String anamnesis;
	
	@ManyToOne
	@JoinColumn(name="diagnostico_id", nullable = false)
	private Diagnostico diagnostico;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "proceso_procedimiento",
			joinColumns = { @JoinColumn(name = "proceso_id") },
			inverseJoinColumns = { @JoinColumn(name = "procedimiento_id") })
	private List<Procedimiento> procedimientos;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "proceso_cuidado",
			joinColumns = { @JoinColumn(name = "proceso_id") },
			inverseJoinColumns = { @JoinColumn(name = "cuidado_id") })
	private Set<Cuidado> cuidados;
	
	@ManyToOne
	@JoinColumn(name="paciente_id", nullable = false)
	private Paciente paciente;
	
	@JsonIgnore
	@OneToMany(mappedBy = "proceso")
    private List<Cura> curas;
	
	private String observaciones;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creacion;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @JsonIgnore
    private Date actualizacion;

	public Proceso() {
		super();
	}

	public Proceso(Paciente paciente) {
		super();
		this.paciente = paciente;
	}

	public String getAnamnesis() {
		return anamnesis;
	}

	public void setAnamnesis(String anamnesis) {
		this.anamnesis = anamnesis;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Date getCreacion() {
		return creacion;
	}

	public Date getActualizacion() {
		return actualizacion;
	}

	public long getId() {
		return id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Cura> getCuras() {
		return curas;
	}

	public void setCuras(List<Cura> curas) {
		this.curas = curas;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<Procedimiento> getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(List<Procedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}

	public Set<Cuidado> getCuidados() {
		return cuidados;
	}

	public void setCuidados(Set<Cuidado> cuidados) {
		this.cuidados = cuidados;
	}
	
	


}
