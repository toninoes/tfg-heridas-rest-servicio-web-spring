package rha.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grupo_procedimiento",
            joinColumns = { @JoinColumn(name = "grupo_id") },
            inverseJoinColumns = { @JoinColumn(name = "procedimiento_id") })
    private List<Procedimiento> procedimientos;
	
	public Grupodiagnostico() {
		super();
	}
	
	public Grupodiagnostico(@NotBlank(message = "Introduzca el nombre del grupo diagnóstico.") String nombre,
			List<Diagnostico> diagnosticos, List<Procedimiento> procedimientos) {
		super();
		this.nombre = nombre;
		this.diagnosticos = diagnosticos;
		this.procedimientos = procedimientos;
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

	public List<Procedimiento> getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(List<Procedimiento> procedimientos) {
		this.procedimientos = procedimientos;
	}		

}
