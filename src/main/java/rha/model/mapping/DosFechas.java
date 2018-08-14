package rha.model.mapping;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class DosFechas {

	@Temporal(TemporalType.DATE)
    private Date fecha1;
	
	@Temporal(TemporalType.DATE)
    private Date fecha2;

	public DosFechas() {
		super();
	}

	public DosFechas(Date fecha1, Date fecha2) {
		super();
		this.fecha1 = fecha1;
		this.fecha2 = fecha2;
	}

	public Date getFecha1() {
		return fecha1;
	}

	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}

	public Date getFecha2() {
		return fecha2;
	}

	public void setFecha2(Date fecha2) {
		this.fecha2 = fecha2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha1 == null) ? 0 : fecha1.hashCode());
		result = prime * result + ((fecha2 == null) ? 0 : fecha2.hashCode());
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
		DosFechas other = (DosFechas) obj;
		if (fecha1 == null) {
			if (other.fecha1 != null)
				return false;
		} else if (!fecha1.equals(other.fecha1))
			return false;
		if (fecha2 == null) {
			if (other.fecha2 != null)
				return false;
		} else if (!fecha2.equals(other.fecha2))
			return false;
		return true;
	}

	
	

}
