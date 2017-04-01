package main.tdt.it.finalproject.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	/**
	 * Convert Date to String 
	 * @param date
	 * @return String with Format yyyyMMdd
	 */
	public static String formatDateToString(Date date) {
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
	public static Date formatStringToDate(String date) throws ParseException {
		SimpleDateFormat sdf = 
			      new SimpleDateFormat("yyyyMMdd");
		return sdf.parse(date);
	}
	/**
	 * Convert Get Year 
	 * @param date with Format yyyyMMdd
	 * @return String 
	 * @throws ParseException 
	 */
	public static int getYear(String date) throws ParseException {
		Calendar instance = Calendar.getInstance();
		instance.setTime(formatStringToDate(date));
		return instance.get(Calendar.YEAR);
	}
}
