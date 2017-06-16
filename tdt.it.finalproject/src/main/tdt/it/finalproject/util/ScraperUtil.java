package main.tdt.it.finalproject.util;

public class ScraperUtil {
	public static double convertStringToDouble(String value) {
		String replace = value.replace(",","");
		return Double.parseDouble(replace);
	}
}
