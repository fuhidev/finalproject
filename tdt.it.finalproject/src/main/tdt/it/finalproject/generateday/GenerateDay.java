package main.tdt.it.finalproject.generateday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GenerateDay {
	/**
	 * Tạo ra một chuỗi th�?i gian kiểu String từ th�?i gian bắt đầu đến th�?i gian kết thúc
	 * @param beginDate Th�?i gian bắt đầu ở định đang yyyyMMdd ví dụ "20121212"
	 * @param endDate Th�?i gian kết thúc ở định đang yyyyMMdd ví dụ "20121212"
	 * @return Một chuỗi giá trị được tạo ra ở định dạng yyyyMMdd
	 * @throws ParseException 
	 */
	public List<String> generate(String beginDate,String endDate){
		SimpleDateFormat sdf = 
			      new SimpleDateFormat("yyyyMMdd");
		Date _beginDate=null,_endDate=null;
		try {
			_beginDate = sdf.parse(beginDate);
			_endDate = sdf.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(_beginDate == null || _endDate == null)
			throw new NullPointerException("Không thể chuyển thời gian ");
		if(_beginDate.after(_endDate))
			throw new DateTimeException("Th�?i gian bắt đầu phải trước th�?i gian kết thúc");
		List<String> result = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		
		Date currentDate = _beginDate;
		result.add(sdf.format(_beginDate));
		while(currentDate.compareTo(_endDate) != 0){
			c.setTime(currentDate);
			c.add(Calendar.DATE, 1);  // number of days to add
			currentDate = c.getTime();
			result.add(sdf.format(currentDate));
		}
		return result;
	}
}
