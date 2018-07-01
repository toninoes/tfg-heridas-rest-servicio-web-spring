package rha.model;

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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "diagnosticos")
public class Diagnostico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Introduzca el código CIE-10 para el diagnóstico.")
	private String codigo;
	
	@Column(unique = true)
	@NotBlank(message = "Introduzca el nombre del diagnóstico.")
	private String nombre;
	
	@JsonIgnore
	@OneToMany(mappedBy = "diagnostico")
    private List<Proceso> procesos;
	
	@ManyToOne
	@JoinColumn(name="grupo_id", nullable = false)
	private Grupodiagnostico grupodiagnostico;
	
	public Diagnostico() {
		super();
	}

	public Diagnostico(@NotBlank(message = "Introduzca el código CIE-10 para el diagnóstico.") String codigo,
			@NotBlank(message = "Introduzca el nombre del diagnóstico.") String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
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

	public List<Proceso> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
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

	
	
	

}
