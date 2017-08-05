package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.modal.GoldPrices;

public class GoldScraper implements IScraper<GoldPrices> {

	private ArrayList<String> elements;
	private Date date;
	
	

	public GoldScraper() {
		super();
	}

	@Override
	public void setElements(List<String> elements) {
		this.elements = (ArrayList<String>) elements;
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
	public GoldPrices getData() throws NotFoundAssetException {
		GoldPrices rs = new GoldPrices();
		if (this.elements == null || this.elements.size() == 0)
			throw new NotFoundAssetException("Gold in " + this.getDate());
		for (int count = 0, i = 0; i < this.elements.size(); i += 3, count++) {
			if (i != this.elements.size() - 2) {
				String name =  this.elements.get(i).toString();
				double buyPrice = Double.parseDouble(this.elements.get(i + 1).toString().replaceAll(",", ""));
				double sellPrice = Double.parseDouble(this.elements.get(i + 2).toString().replaceAll(",", ""));
				Date date = this.getDate();
				GoldPrice js = new GoldPrice(count,name,sellPrice,date);
				js.setBuyPrice(buyPrice);
				rs.add(js);
			}
		}
		return rs;
	}


}
