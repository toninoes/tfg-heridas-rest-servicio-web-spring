package rha.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fecha {

	public static String fechaHoraSP(Date fechaExpiracion) {
		SimpleDateFormat formateador = new SimpleDateFormat(
				   "dd'/'MM'/'yyyy 'a las' HH:mm 'horas'", new Locale("es_ES"));
		
		return formateador.format(fechaExpiracion);
	}
}
