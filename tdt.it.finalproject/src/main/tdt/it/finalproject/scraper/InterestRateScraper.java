package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.modal.InterestRate;
import main.tdt.it.finalproject.modal.InterestRatePrices;

public class InterestRateScraper implements IScraper<InterestRatePrices> {

	private ArrayList<String> elements;
	private Date date;
	
	

	public InterestRateScraper() {
		super();
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		if(this.date == null){
			return Calendar.getInstance().getTime();
		}
		return this.date;
	}
	@Override
	public void setElements(List<String> elements) {
		this.elements = (ArrayList<String>) elements;
	}

	@Override
	public InterestRatePrices getData() throws NotFoundAssetException {
		InterestRatePrices rs = new InterestRatePrices();
		if (this.elements == null || this.elements.size() == 0)
			throw new NotFoundAssetException("InterestRate in " + this.getDate());
		for (int i = 0; i < this.elements.size(); i += 3) {

			if (i != this.elements.size() - 3) {
				String period = this.elements.get(i).toString();
				String name = this.elements.get(i + 1).toString();
				float percent = Float.parseFloat(this.elements.get(i + 2).toString());
				InterestRate js = new InterestRate(period,name ,percent,Calendar.getInstance().getTime());
				
				Pattern pattern = Pattern.compile("/images/v2011/logo/");
				Matcher matcher = pattern.matcher(js.getNameBank());
				if(matcher.find()){
					js.setNameBank(js.getNameBank().substring(matcher.end(0),js.getNameBank().length()-4));
					Matcher matcher2 = Pattern.compile("_").matcher(js.getNameBank());
					if(matcher2.find()){
						int start = matcher2.start();
						js.setNameBank(js.getNameBank().substring(0,start));
						int lastIndex = js.getNameBank().lastIndexOf("-");
						js.setNameBank(js.getNameBank().substring(lastIndex+1));
					}
				}
				rs.add(js);
			}
		}
		return rs;
	}

}
