package rha.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sala_configs")
public class SalaConfig {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Min(0)
	private Integer cupo;
	
	@NotNull
	@Min(0)
	@Max(23)
	private Integer horaini;
	
	@NotNull
	@Min(0)
	@Max(59)
	private Integer minini;
	
	@NotNull
	@Min(0)
	@Max(23)
	private Integer horafin;
	
	@NotNull
	@Min(0)
	@Max(59)
	private Integer minfin;
	
	private Boolean lunes, martes, miercoles, jueves, viernes, sabado, domingo;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sala_id", nullable = false)
	private Sala sala;
		
	public SalaConfig() {
		super();
	}
	
	public SalaConfig(@NotNull @Min(0) Integer cupo, @NotNull @Min(0) @Max(23) Integer horaini,
			@NotNull @Min(0) @Max(59) Integer minini, @NotNull @Min(0) @Max(23) Integer horafin,
			@NotNull @Min(0) @Max(59) Integer minfin, Boolean lunes, Boolean martes, Boolean miercoles, Boolean jueves,
			Boolean viernes, Boolean sabado, Boolean domingo) {
		super();
		this.cupo = cupo;
		this.horaini = horaini;
		this.minini = minini;
		this.horafin = horafin;
		this.minfin = minfin;
		this.lunes = lunes;
		this.martes = martes;
		this.miercoles = miercoles;
		this.jueves = jueves;
		this.viernes = viernes;
		this.sabado = sabado;
		this.domingo = domingo;
	}
	
	public SalaConfig(@NotNull @Min(0) Integer cupo, @NotNull @Min(0) @Max(23) Integer horaini,
			@NotNull @Min(0) @Max(59) Integer minini, @NotNull @Min(0) @Max(23) Integer horafin,
			@NotNull @Min(0) @Max(59) Integer minfin, Boolean lunes, Boolean martes, Boolean miercoles, Boolean jueves,
			Boolean viernes, Boolean sabado, Boolean domingo, Sala sala) {
		super();
		this.cupo = cupo;
		this.horaini = horaini;
		this.minini = minini;
		this.horafin = horafin;
		this.minfin = minfin;
		this.lunes = lunes;
		this.martes = martes;
		this.miercoles = miercoles;
		this.jueves = jueves;
		this.viernes = viernes;
		this.sabado = sabado;
		this.domingo = domingo;
		this.sala = sala;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getCupo() {
		return cupo;
	}

	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}

	public Integer getHoraini() {
		return horaini;
	}

	public void setHoraini(Integer horaini) {
		this.horaini = horaini;
	}

	public Integer getMinini() {
		return minini;
	}

	public void setMinini(Integer minini) {
		this.minini = minini;
	}

	public Integer getHorafin() {
		return horafin;
	}

	public void setHorafin(Integer horafin) {
		this.horafin = horafin;
	}

	public Integer getMinfin() {
		return minfin;
	}

	public void setMinfin(Integer minfin) {
		this.minfin = minfin;
	}

	public Boolean getLunes() {
		return lunes;
	}

	public void setLunes(Boolean lunes) {
		this.lunes = lunes;
	}

	public Boolean getMartes() {
		return martes;
	}

	public void setMartes(Boolean martes) {
		this.martes = martes;
	}

	public Boolean getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(Boolean miercoles) {
		this.miercoles = miercoles;
	}

	public Boolean getJueves() {
		return jueves;
	}

	public void setJueves(Boolean jueves) {
		this.jueves = jueves;
	}

	public Boolean getViernes() {
		return viernes;
	}

	public void setViernes(Boolean viernes) {
		this.viernes = viernes;
	}

	public Boolean getSabado() {
		return sabado;
	}

	public void setSabado(Boolean sabado) {
		this.sabado = sabado;
	}

	public Boolean getDomingo() {
		return domingo;
	}

	public void setDomingo(Boolean domingo) {
		this.domingo = domingo;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	
	

	
	
}
