package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.jsondata.DollarPrice1;
import main.tdt.it.finalproject.modal.DollarPrices;

public class DollarScraper implements IScraper<DollarPrices> {

	private ArrayList<String> elements;
	private String date;
	
	

	public DollarScraper() {
		super();
	}

	@Override
	public void setElements(List<String> elements) {
		this.elements = (ArrayList<String>) elements;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public DollarPrices getDatas() throws NotFoundAssetException {
		DollarPrices rs = new DollarPrices();
		if (this.elements == null || this.elements.size() == 0)
			throw new NotFoundAssetException("Dollar in " + this.date);
		for (int count = 0, i = 0; i < this.elements.size(); i += 4, count++) {
			if (i != this.elements.size() - 2) {
					DollarPrice1 js = new DollarPrice1(count, this.elements.get(i).toString(), this.elements.get(i + 1).toString(),
							this.elements.get(i + 2).toString(), this.elements.get(i + 3).toString(), date);
					rs.add(js);

				}
		}
		return rs;
	}


}
