package main.tdt.it.finalproject.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	
	public static String formatDateToString(Date date,String format) {
		SimpleDateFormat sdf = 
			      new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * Convert Date to String 
	 * @param date
	 * @return String with Format yyyyMMdd
	 */
	public static String formatDateToString(Date date) {
		return formatDateToString(date, "yyyyMMdd");
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
	
	public static int toTimeStamp(Date time) {
		Timestamp timestamp = new Timestamp(time.getTime());
		return timestamp.getNanos();
	}
}
