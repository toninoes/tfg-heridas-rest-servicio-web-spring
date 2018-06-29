package rha.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "procedimientos")
public class Procedimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Introduzca el código CPM para el procedimiento.")
	private String cpm;
	
	@Column(unique = true)
	@NotBlank(message = "Introduzca el nombre del procedimiento.")
	private String nombre;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "procedimientos")
	private List<Grupodiagnostico> gruposdiagnostico;
	
	@JsonIgnore
	@OneToMany(mappedBy = "procedimiento")
    private List<Proceso> procesos;

	public Procedimiento() {
		super();
	}

	public Procedimiento(@NotBlank(message = "Introduzca el código CPM para el procedimiento.") String cpm,
			@NotBlank(message = "Introduzca el nombre del procedimiento.") String nombre,
			List<Grupodiagnostico> gruposdiagnostico) {
		super();
		this.cpm = cpm;
		this.nombre = nombre;
		this.gruposdiagnostico = gruposdiagnostico;
	}

	public String getCpm() {
		return cpm;
	}

	public void setCpm(String cpm) {
		this.cpm = cpm;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	

	public List<Grupodiagnostico> getGruposdiagnostico() {
		return gruposdiagnostico;
	}

	public void setGruposdiagnostico(List<Grupodiagnostico> gruposdiagnostico) {
		this.gruposdiagnostico = gruposdiagnostico;
	}

	public long getId() {
		return id;
	}
	
	
}
