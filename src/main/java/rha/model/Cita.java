package rha.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "citas")
public class Cita {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false) 
	private Paciente paciente;
	
	@ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
	private Sala sala;
	
	// campos adicionales (atributos de enlace)
	
	@NotNull(message = "Introduzca una fecha para la cita")
	private Date fecha;
	
	@NotNull(message = "Introduzca un número de orden")
	private Long orden;

	public Cita() {
		super();
	}

	public Cita(Paciente paciente, Sala sala, @NotNull(message = "Introduzca una fecha para la cita") Date fecha) {
		super();
		this.paciente = paciente;
		this.sala = sala;
		this.fecha = fecha;
	}
		
	public Cita(Paciente paciente, Sala sala, @NotNull(message = "Introduzca una fecha para la cita") Date fecha,
			Long orden) {
		super();
		this.paciente = paciente;
		this.sala = sala;
		this.fecha = fecha;
		this.orden = orden;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public long getId() {
		return id;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}
}
