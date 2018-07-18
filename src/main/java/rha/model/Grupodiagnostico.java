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
	

}
