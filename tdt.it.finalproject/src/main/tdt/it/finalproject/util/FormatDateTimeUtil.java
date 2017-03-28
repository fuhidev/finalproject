package main.tdt.it.finalproject.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDateTimeUtil {
	/**
	 * Convert Date to String 
	 * @param date
	 * @return String with Format yyyyMMdd
	 */
	public static String DateToString(Date date) {
		SimpleDateFormat sdf = 
			      new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	/**
	 * Convert String to Date
	 * @param date with format yyyyMMdd
	 * @return Date parsed
	 * @throws ParseException
	 */
	public static Date StringToDate(String date) throws ParseException {
		SimpleDateFormat sdf = 
			      new SimpleDateFormat("yyyyMMdd");
		return sdf.parse(date);
	}
}
