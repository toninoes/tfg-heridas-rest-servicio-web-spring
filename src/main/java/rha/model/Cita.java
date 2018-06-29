package rha.model;

import java.util.Date;

import javax.persistence.CascadeType;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PACIENTE_ID") 
	private Paciente paciente;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SALA_ID")
	private Sala sala;
	
	// campo adicional (atributo de enlace)
	
	@NotNull(message = "Introduzca una fecha para la cita")
	private Date fecha;

	public Cita() {
		super();
	}

	public Cita(Paciente paciente, Sala sala, @NotNull(message = "Introduzca una fecha para la cita") Date fecha) {
		super();
		this.paciente = paciente;
		this.sala = sala;
		this.fecha = fecha;
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
	
	
	
}
