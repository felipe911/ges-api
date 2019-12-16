package br.com.ges.api.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

	/**
	 * 
	 * Converte LocalDate para String
	 * 
	 */
	public static String localDateParaString(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formatoString = localDate.format(formatter);

		return formatoString;
	}
	
	public static Boolean estaEntre(LocalDate dataValidar, String dataInicio, String dataFim) {
		
		if(dataValidar.isAfter(LocalDate.parse(dataInicio)) && dataValidar.isBefore(LocalDate.parse(dataFim)))
			return true;
		
		return false;
	}

}
