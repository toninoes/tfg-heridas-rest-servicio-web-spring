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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((grupodiagnostico == null) ? 0 : grupodiagnostico.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((procesos == null) ? 0 : procesos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diagnostico other = (Diagnostico) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (grupodiagnostico == null) {
			if (other.grupodiagnostico != null)
				return false;
		} else if (!grupodiagnostico.equals(other.grupodiagnostico))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (procesos == null) {
			if (other.procesos != null)
				return false;
		} else if (!procesos.equals(other.procesos))
			return false;
		return true;
	}
	
	
	

}
