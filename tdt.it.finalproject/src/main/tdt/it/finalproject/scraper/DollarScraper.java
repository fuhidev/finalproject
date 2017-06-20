package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.DollarPrices;
import main.tdt.it.finalproject.util.DateTimeUtil;

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
		for (int i = 0; i < this.elements.size(); i += 2) {
			if (i != this.elements.size() - 1) {
				DollarPrice js = new DollarPrice(this.elements.get(i).toString(),
						Double.parseDouble(this.elements.get(i + 1).toString().replace(",", "")), DateTimeUtil.formatStringToDate(date));
				js.setPrice(Double.parseDouble(this.elements.get(i + 1).toString().replace(",", "")));
				rs.add(js);

			}
		}
		return rs;
	}

}
