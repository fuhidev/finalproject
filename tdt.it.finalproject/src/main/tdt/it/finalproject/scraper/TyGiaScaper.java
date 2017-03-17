package main.tdt.it.finalproject.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.jsondata.GoldPrice;

public class TyGiaScaper {

	private String cssQuery = "#ratetb tr:first-child td.c1 b,#ratetb tr:first-child td span.c2,#ratetb tr:first-child td span.c3, #ratetb tr:first-child td span.c4";
	private String date;
	public final String URL = "https://www.tygia.com/?nganhang=VIETCOM&ngay=";

	public TyGiaScaper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TyGiaScaper(String cssQuery, String date) {
		super();
		this.cssQuery = cssQuery;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the cssQuery
	 */
	public String getCssQuery() {
		return cssQuery;
	}

	/**
	 * @param cssQuery
	 *            the cssQuery to set
	 */
	public void setCssQuery(String cssQuery) {
		this.cssQuery = cssQuery;
	}

	private ArrayList<String> exportDB() {
		ArrayList<String> result = new ArrayList<String>();
		Document doc = null;
		try {
			doc = Jsoup.connect(this.getUrl()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (doc != null) {
			Elements aElements = doc.select(this.getCssQuery());
			for (Element aElement : aElements) {
				result.add(aElement.text());
			}
		}
		return result;

	}

	private String getUrl() {
		return URL + date;
	}

	public List<AssetPrice> getGoldData() throws IOException {
		ArrayList<String> tmpExp = new ArrayList<String>();
		List<AssetPrice> rs = new ArrayList<AssetPrice>();
		tmpExp = this.exportDB();

		for (int count = 0, i = 0; i < tmpExp.size(); i += 3, count++) {
			if (i != tmpExp.size() - 2) {
				GoldPrice js = new GoldPrice(count, tmpExp.get(i).toString(), tmpExp.get(i + 1).toString(),
						tmpExp.get(i + 2).toString(), date);
				rs.add(js);
			}
		}
		return rs;
	}

	public List<AssetPrice> getDollarData() {
		ArrayList<String> tmpExp = new ArrayList<String>();
		List<AssetPrice> rs = new ArrayList<AssetPrice>();
		tmpExp = this.exportDB();
		for (int count = 0, i = 0; i < tmpExp.size(); i += 4, count++) {
			if (i != tmpExp.size() - 3) {
				DollarPrice js = new DollarPrice(count, tmpExp.get(i).toString(), tmpExp.get(i + 1).toString(),
						tmpExp.get(i + 2).toString(), tmpExp.get(i + 3).toString(), date);
				rs.add(js);

			}
		}
		return rs;

	}

}
