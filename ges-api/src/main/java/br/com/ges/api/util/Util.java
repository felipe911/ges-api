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
}
