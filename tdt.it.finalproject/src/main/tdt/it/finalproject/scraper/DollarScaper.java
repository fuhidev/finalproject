package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;

public class DollarScaper implements IScaper {

	private ArrayList<String> elements;
	private String date;
	
	

	public DollarScaper() {
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
	public List<AssetPrice> getDatas() throws NotFoundAssetException {
		List<AssetPrice> rs = new ArrayList<AssetPrice>();
		if (this.elements == null || this.elements.size() == 0)
			throw new NotFoundAssetException("Dollar in " + this.date);
		for (int count = 0, i = 0; i < this.elements.size(); i += 4, count++) {
			if (i != this.elements.size() - 2) {
					DollarPrice js = new DollarPrice(count, this.elements.get(i).toString(), this.elements.get(i + 1).toString(),
							this.elements.get(i + 2).toString(), this.elements.get(i + 3).toString(), date);
					rs.add(js);

				}
		}
		return rs;
	}

}
