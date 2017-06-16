package test.tdt.it.finalproject.util;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import main.tdt.it.finalproject.util.DateTimeUtil;

public class DateTimeUtilTest {
	
	@Test
	public void toTimeStamp() {
		Calendar date = Calendar.getInstance();
		date.set(2010, 10, 10, 0, 0, 0);
		System.out.println(date.getTime());
		System.out.println(DateTimeUtil.toTimeStamp(date.getTime()));
		assertEquals(1497509607,DateTimeUtil.toTimeStamp(date.getTime()));
	}

}
