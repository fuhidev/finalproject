package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.InterestRate;

public class InterestRateScraper implements IScraper {

	private ArrayList<String> elements;
	private String date;
	
	

	public InterestRateScraper() {
		super();
	}

	@Override
	public void setElements(List<String> elements) {
		this.elements = (ArrayList<String>) elements;
	}

	@Override
	public List<AssetPrice> getDatas() throws NotFoundAssetException {
		List<AssetPrice> rs = new ArrayList<AssetPrice>();
		if (this.elements == null || this.elements.size() == 0)
			throw new NotFoundAssetException("InterestRate in " + this.date);
		for (int i = 0; i < this.elements.size(); i += 5) {

			if (i != this.elements.size() - 4) {
				
				InterestRate js = new InterestRate(this.elements.get(i).toString(), this.elements.get(i + 2).toString(),
						this.elements.get(i + 3).toString());
				
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
