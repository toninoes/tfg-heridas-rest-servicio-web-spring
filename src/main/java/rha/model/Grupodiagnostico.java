package rha.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Grupodiagnostico {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	@NotBlank(message = "Introduzca el nombre del grupo diagnóstico.")
	private String nombre;
	
	@JsonIgnore
	@OneToMany(mappedBy = "grupodiagnostico")
    private List<Diagnostico> diagnosticos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "grupodiagnostico")
    private List<Cuidado> cuidados;
	
	public Grupodiagnostico() {
		super();
	}
	
	public Grupodiagnostico(@NotBlank(message = "Introduzca el nombre del grupo diagnóstico.") String nombre,
			List<Diagnostico> diagnosticos) {
		super();
		this.nombre = nombre;
		this.diagnosticos = diagnosticos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public List<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(List<Diagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}
	
	public List<Cuidado> getCuidados() {
		return cuidados;
	}

	public void setCuidados(List<Cuidado> cuidados) {
		this.cuidados = cuidados;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuidados == null) ? 0 : cuidados.hashCode());
		result = prime * result + ((diagnosticos == null) ? 0 : diagnosticos.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Grupodiagnostico other = (Grupodiagnostico) obj;
		if (cuidados == null) {
			if (other.cuidados != null)
				return false;
		} else if (!cuidados.equals(other.cuidados))
			return false;
		if (diagnosticos == null) {
			if (other.diagnosticos != null)
				return false;
		} else if (!diagnosticos.equals(other.diagnosticos))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
