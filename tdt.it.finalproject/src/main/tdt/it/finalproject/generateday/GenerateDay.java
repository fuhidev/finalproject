package main.tdt.it.finalproject.generateday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public class GenerateDay {
	/**
	 * Táº¡o ra má»™t chuá»—i thï¿½?i gian kiá»ƒu String tá»« thï¿½?i gian báº¯t
	 * Ä‘áº§u Ä‘áº¿n thï¿½?i gian káº¿t thÃºc
	 * 
	 * @param beginDate
	 *            Thï¿½?i gian báº¯t Ä‘áº§u á»Ÿ Ä‘á»‹nh Ä‘ang yyyyMMdd vÃ­ dá»¥
	 *            "20121212"
	 * @param endDate
	 *            Thï¿½?i gian káº¿t thÃºc á»Ÿ Ä‘á»‹nh Ä‘ang yyyyMMdd vÃ­ dá»¥
	 *            "20121212"
	 * @return Má»™t chuá»—i giÃ¡ trá»‹ Ä‘Æ°á»£c táº¡o ra á»Ÿ Ä‘á»‹nh dáº¡ng
	 *         yyyyMMdd
	 * @throws ParseException
	 */
	public List<String> generate(String beginDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date _beginDate = null, _endDate = null;
		try {
			_beginDate = sdf.parse(beginDate);
			_endDate = sdf.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (_beginDate == null || _endDate == null)
			throw new NullPointerException("Không thể chuyển thành thời gian");
		if (_beginDate.after(_endDate))
			throw new DateTimeException(
					"Thời gian bắt đầu không thể lớn hơn thời gian kết thúc");
		List<String> result = new ArrayList<>();
		Calendar c = Calendar.getInstance();

		Date currentDate = _beginDate;
		result.add(sdf.format(_beginDate));
		while (currentDate.compareTo(_endDate) != 0) {
			c.setTime(currentDate);
			c.add(Calendar.DATE, 1); // number of days to add
			currentDate = c.getTime();
			result.add(sdf.format(currentDate));
		}
		return result;
	}

	public List<String> genrateYear(int startYear, int endYear) {
		List<String> result = new ArrayList<>();
		if(startYear <= 0 || endYear <= 0)throw new NullPointerException("Không thể chuyển thành thời gian");
		if (startYear > endYear)
			throw new DateTimeException(
					"Thời gian bắt đầu không thể lớn hơn thời gian kết thúc");
		try {
			for (int i = startYear; i < endYear; i++) {
				result.add("" + i);
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return result;
	}
}
