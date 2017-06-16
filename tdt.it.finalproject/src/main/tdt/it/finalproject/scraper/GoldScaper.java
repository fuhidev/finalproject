package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.GoldPrice;

public class GoldScaper implements IScaper {

	private ArrayList<String> elements;
	private String date;
	
	

	public GoldScaper() {
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
	public List<AssetPrice> getDatas() throws NotFoundAssetException {
		List<AssetPrice> rs = new ArrayList<AssetPrice>();
		if (this.elements == null || this.elements.size() == 0)
			throw new NotFoundAssetException("Gold in " + this.date);
		for (int count = 0, i = 0; i < this.elements.size(); i += 3, count++) {
			if (i != this.elements.size() - 2) {
				GoldPrice js = new GoldPrice(count, this.elements.get(i).toString(), this.elements.get(i + 1).toString(),
						this.elements.get(i + 2).toString(), date);
				rs.add(js);
			}
		}
		return rs;
	}

}
