

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GenerateDay {
	private Date beginDate;
	private Date endDate;
	
	/**
	 * T?o ra m?t chu?i th?i gian ki?u String t? th?i gian b?t ??u ??n th?i gian k?t thúc
	 * @param beginDate Th?i gian b?t ??u ? ??nh ?ang yyyyMMdd ví d? "20121212"
	 * @param endDate Th?i gian k?t thúc ? ??nh ?ang yyyyMMdd ví d? "20121212"
	 * @return M?t chu?i giá tr? ???c t?o ra ? ??nh d?ng yyyyMMdd
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
			throw new NullPointerException("Không th? chuy?n th?i gian ");
		if(_beginDate.after(_endDate))
			throw new DateTimeException("Th?i gian b?t ??u ph?i tr??c th?i gian k?t thúc");
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