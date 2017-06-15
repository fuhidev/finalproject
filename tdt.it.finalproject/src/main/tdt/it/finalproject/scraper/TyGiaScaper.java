package main.tdt.it.finalproject.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.jsondata.GoldPrice;
import main.tdt.it.finalproject.jsondata.InterestRate;
@Deprecated
public class TyGiaScaper {

	private String date;
//	public final String URL = "https://www.tygia.com/?nganhang=VIETCOM&ngay=";
	public final String URL ="http://vietbao.vn/vn/lai-suat-tiet-kiem/";
	private String cssQueryDollar = "#ratetb tr:first-child td.c1 b,#ratetb tr:first-child td span.c2,#ratetb tr:first-child td span.c3, #ratetb tr:first-child td span.c4";
	private String cssQueryGold = "#gold_tb #goldtb td.c1 b,#gold_tb #goldtb span.c2,#gold_tb #goldtb span.c4";
	private String cssQueryInterestRate = ".ruler1 tr:gt(0) td:nth-child(1),.ruler1 tr:gt(0) td:nth-child(2) img[src],.ruler1 tr:gt(0) td:nth-child(3)";
	private Document doc = null;

	public TyGiaScaper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TyGiaScaper(String date) {
		super();
		this.date = date;
	}

	public String getCssQueryDollar() {
		return cssQueryDollar;
	}

	public void setCssQueryDollar(String cssQueryDollar) {
		this.cssQueryDollar = cssQueryDollar;
	}

	public String getCssQueryGold() {
		return cssQueryGold;
	}

	public void setCssQueryGold(String cssQueryGold) {
		this.cssQueryGold = cssQueryGold;
	}

	public TyGiaScaper(String date, String cssQueryDollar, String cssQueryGold) {
		super();
		this.date = date;
		this.cssQueryDollar = cssQueryDollar;
		this.cssQueryGold = cssQueryGold;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Document getDoc() throws IOException {
		if (doc == null)
			return Jsoup.connect(this.getUrl()).get();
		return doc;
	}

	private ArrayList<String> getHtml(String cssQuery) {
		ArrayList<String> result = new ArrayList<String>();

		try {
			doc = getDoc();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (doc != null) {
			Elements aElements = doc.select(cssQuery);
			for (Element aElement : aElements) {
				if(aElement.hasText()){
					result.add(aElement.text());
					
				}
				result.add(aElement.attr("src"));
			}
		}
		return result;

	}

	private String getUrl() {
		return URL + date;
	}

	public List<AssetPrice> getGoldData() throws NotFoundAssetException {
		System.out.println(String.format("Dang duyet du lieu cua Gold vao ngay %s", date));
		ArrayList<String> tmpExp = new ArrayList<String>();
		List<AssetPrice> rs = new ArrayList<AssetPrice>();
		tmpExp = this.getHtml(this.cssQueryGold);
		if (tmpExp == null || tmpExp.size() == 0)
			throw new NotFoundAssetException("Gold in " + this.date);
		for (int count = 0, i = 0; i < tmpExp.size(); i += 3, count++) {
			if (i != tmpExp.size() - 2) {
				GoldPrice js = new GoldPrice(count, tmpExp.get(i).toString(), tmpExp.get(i + 1).toString(),
						tmpExp.get(i + 2).toString(), date);
				rs.add(js);
			}
		}
		return rs;
	}

	public List<AssetPrice> getDollarData() throws NotFoundAssetException {
		System.out.println(String.format("Dang duyet du lieu cua Dollar vao ngay %s", date));
		ArrayList<String> tmpExp = new ArrayList<String>();
		List<AssetPrice> rs = new ArrayList<AssetPrice>();
		tmpExp = this.getHtml(this.cssQueryDollar);
		if (tmpExp == null || tmpExp.size() == 0)
			throw new NotFoundAssetException("Dollar in " + this.date);
		for (int count = 0, i = 0; i < tmpExp.size(); i += 4, count++) {

			if (i != tmpExp.size() - 3) {
				DollarPrice js = new DollarPrice(count, tmpExp.get(i).toString(), tmpExp.get(i + 1).toString(),
						tmpExp.get(i + 2).toString(), tmpExp.get(i + 3).toString(), date);
				rs.add(js);

			}
		}
		return rs;

	}
	
	public List<InterestRate> getInterestRate() throws NotFoundAssetException {
		System.out.println(String.format("Dang duyet du lieu của lãi suat "));
		ArrayList<String> tmpExp = new ArrayList<String>();
		List<InterestRate> rs = new ArrayList<InterestRate>();
		tmpExp = this.getHtml(this.cssQueryInterestRate);
		if (tmpExp == null || tmpExp.size() == 0)
			throw new NotFoundAssetException("Khong tim thay trang ");
		for (int i = 0; i < tmpExp.size(); i += 5) {

			if (i != tmpExp.size() - 4) {
				
				InterestRate js = new InterestRate(tmpExp.get(i).toString(), tmpExp.get(i + 2).toString(),
						tmpExp.get(i + 3).toString());
				
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
				System.out.println(js.getKyHan() + "-" + js.getNameBank() + "-" + js.getPercentInterestRate());
			}
		}
		return rs;
	}

}
