package libreria;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Fecha {

	//  Métodos static que retornan valor (sin parámetros)
	public static String fechaActual() {
		int dd, mm, aa;
		Calendar c = new GregorianCalendar();
		dd = c.get(Calendar.DAY_OF_MONTH);
		mm = c.get(Calendar.MONTH) + 1;
		aa = c.get(Calendar.YEAR);
		return ajustar(dd) + "/" + ajustar(mm) + "/" + aa;
	}
	public static String horaActual() {
		int hh, mm, ss;
		Calendar c = new GregorianCalendar();
		hh = c.get(Calendar.HOUR_OF_DAY);
		mm = c.get(Calendar.MINUTE);
		ss = c.get(Calendar.SECOND);
		return ajustar(hh) + ":" + ajustar(mm) + ":" + ajustar(ss);
	}
	//  Métodos static que retornan valor (con parámetros)
	public static String enTextoFecha(String fecha) {
		String[] s = fecha.split("/");
		if (s.length == 3)
			return s[0] + " de " + obtenerMes(s[1]) + " de " + s[2];
		else
			return "";
	}
	public static String obtenerMes(String mes) {
		switch (mes) {
			case "01": return "Enero";
			case "02": return "Febrero";
			case "03": return "Marzo";
			case "04": return "Abril";
			case "05": return "Mayo";
			case "06": return "Junio";
			case "07": return "Julio";
			case "08": return "Agosto";
			case "09": return "Setiembre";
			case "10": return "Octubre";
			case "11": return "Noviembre";
			default: return "Diciembre";
		}
	}
	public static int diasTranscurridos(String fechaInicial, String fechaFinal) {
		try {
			SimpleDateFormat adt = new SimpleDateFormat("dd/MM/yyyy");
			Date fi = adt.parse(fechaInicial),
			     ff = adt.parse(fechaFinal);
			return (int) ((ff.getTime() - fi.getTime()) / 86400000);
		}
		catch (Exception e) {
			return 0;
		}
	}
	private static String ajustar(int numero) {
		return String.format("%02d", numero);
	}
	
}