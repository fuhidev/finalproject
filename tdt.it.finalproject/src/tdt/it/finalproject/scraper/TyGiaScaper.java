package tdt.it.finalproject.scraper;

import tdt.it.finalproject.generateDay.GenerateDay;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tdt.it.finalproject.jsondata.AssetPrice;
import tdt.it.finalproject.jsondata.DollarPrice;
import tdt.it.finalproject.jsondata.GoldPrice;

public class TyGiaScaper {

	private String url;
	private String cssQuery;
	private String dateStart;
	private String dateEnd;
	private static Document doc;
	private String date;
	private List<String> link = null;

	public Document getDoc() {
		return doc;
	}

	public List<String> getLink() {
		return link;
	}

	public void setLink(List<String> link) {
		this.link = link;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public TyGiaScaper(String url, String cssQuery) {
		super();
		this.url = url;
		this.cssQuery = cssQuery;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public TyGiaScaper(String url, String cssQuery, String dateStart,
			String dateEnd) {
		super();
		this.url = url;
		this.cssQuery = cssQuery;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;

	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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

	private ArrayList<String> exportDB() throws IOException {
		this.link = new GenerateDay().generate(this.getDateStart(),
				this.getDateEnd());
		ArrayList<String> result = new ArrayList<String>();
		for (String item : link) {
			doc = Jsoup.connect(this.getUrl() + item).get();
			Elements aElements = doc.select(this.getCssQuery());
			for (Element aElement : aElements) {
				result.add(aElement.text());
			}
		}
		return result;

	}

	public List<AssetPrice> getGoldData() throws IOException {
		ArrayList<String> tmpExp = new ArrayList<String>();
		List<AssetPrice> rs = new ArrayList<AssetPrice>();
		tmpExp = this.exportDB();
		for (int j = 0; j < link.size(); j++) {
			for (int i = 0; i < tmpExp.size() - 1; i++) {
				if (i != tmpExp.size() - 2) {
					date = this.link.get(j).substring(6, 8) + "-"
							+ this.link.get(j).substring(4, 6) + "-"
							+ this.link.get(j).substring(0, 4);
					GoldPrice js = new GoldPrice(i, tmpExp.get(i).toString(),
							tmpExp.get(i + 1).toString(), tmpExp.get(i + 2)
									.toString(), date);
					rs.add(js);
				}
				i += 2;
			}
		}
		return rs;
	}

	public List<AssetPrice> getDollarData() throws IOException {
		ArrayList<String> tmpExp = new ArrayList<String>();
		List<AssetPrice> rs = new ArrayList<AssetPrice>();
		tmpExp = this.exportDB();
		for (int j = 0; j < link.size(); j++) {
			for (int i = 0; i < tmpExp.size() - 1; i++) {
				if (i != tmpExp.size() - 3) {
					DollarPrice js = new DollarPrice(i, tmpExp.get(i)
							.toString(), tmpExp.get(i + 1).toString(), tmpExp
							.get(i + 2).toString(), tmpExp.get(i + 3)
							.toString(),date);
					rs.add(js);
					i += 3;
				}
			}
		}
		return rs;

	}
	
	public void export(List<AssetPrice> goldPrices, String fileName) {
		System.out.println("Dang doc du lieu");
		String JSONResult = JSONArray.toJSONString(goldPrices);
		System.out.println("Tien hanh ghi du lieu");
		try (FileWriter file = new FileWriter(fileName + ".txt")) {
			file.write(JSONResult);
			System.out.println("Successfully Copied JSON Object to File...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
//		TyGiaScaper jsoup = new TyGiaScaper(
//				"https://www.tygia.com/?nganhang=VIETCOM&ngay=",
//				"#gold_tb #goldtb td.c1 b,#gold_tb #goldtb .c2,#gold_tb #goldtb .c4",
//				"20170312", "20170313");
//		jsoup.export(jsoup.getGoldData(), "test");
		 TyGiaScaper jsoup2 = new TyGiaScaper(
		 "https://www.tygia.com/?nganhang=VIETCOM&ngay=",
		 "#ratetb tr:first-child td.c1 b,#ratetb tr:first-child td span.c2,#ratetb tr:first-child td span.c3, #ratetb tr:first-child td span.c4",
		 "20170313", "20170313");
		 jsoup2.export(jsoup2.getDollarData(), "ok1");
	}
}
