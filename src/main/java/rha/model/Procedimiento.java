package rha.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String codigo;
	
	@Column(unique = true)
	@NotBlank(message = "Introduzca el nombre del procedimiento.")
	private String nombre;

	@JsonIgnore
	@OneToMany(mappedBy = "procedimiento")
    private List<Proceso> procesos;

	public Procedimiento() {
		super();
	}

	public Procedimiento(@NotBlank(message = "Introduzca el código CPM para el procedimiento.") String codigo,
			@NotBlank(message = "Introduzca el nombre del procedimiento.") String nombre) {
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

	public long getId() {
		return id;
	}

	public List<Proceso> getProcesos() {
		return procesos;
	}

	public void setProcesos(List<Proceso> procesos) {
		this.procesos = procesos;
	}
	
	
	
	
}
