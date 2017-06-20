package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.modal.WorldGold;
import main.tdt.it.finalproject.modal.WorldGoldPrices;

public class WorldGoldScraper implements IScraper<WorldGoldPrices>{
	private String date;
	private ArrayList<String> elements;
	
	public WorldGoldScraper() {
		super();
	}

	@Override
	public void setElements(List<String> elements) {
		this.elements = (ArrayList<String>) elements;
	}
	
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public WorldGoldPrices getDatas() throws NotFoundAssetException {
		WorldGoldPrices rs = new WorldGoldPrices();
		if (this.elements == null || this.elements.size() == 0)
			throw new NotFoundAssetException("Gold in " + this.date);
		for (int count = 0, i = 0; i < this.elements.size(); i += 3, count++) {
			if (i != this.elements.size() - 2) {
				WorldGold js = new WorldGold(this.elements.get(i).toString(), this.elements.get(i + 1).toString(),
						this.elements.get(i + 2).toString(), date);
				rs.add(js);
			}
		}
		return rs;
	}

	
}
