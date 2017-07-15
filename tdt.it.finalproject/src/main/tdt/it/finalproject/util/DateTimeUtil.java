package main.tdt.it.finalproject.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	public static int getDate(Date date,int field) {
			Calendar instance = Calendar.getInstance();
			instance.setTime(date);
			return instance.get(field);
	}
	public static Date addDays(Date date,int day) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, day);
		return c.getTime();
	}
	
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
	public static Date formatStringToDate(String date) {
		return formatStringToDate(date,"yyyyMMdd");
	}
	public static Date formatStringToDate(String date,String format) {
		try {
		SimpleDateFormat sdf = 
			      new SimpleDateFormat(format);
		
			return sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	public static java.sql.Date convertUtilToSQL(Date date) {
		return new java.sql.Date(date.getTime());
	}
}
