package test.tdt.it.finalproject.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import main.tdt.it.finalproject.util.DateTimeUtil;

public class TestDateTimeUtil {

	@Test
	public void formatStringToDate() throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");
		Date ex = 
				myFormat.parse("20170616");
		System.out.println(ex);
		System.out.println(DateTimeUtil.formatStringToDate("20170616"));
	}

}
