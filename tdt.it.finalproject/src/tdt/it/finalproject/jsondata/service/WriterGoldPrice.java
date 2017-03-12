package tdt.it.finalproject.jsondata.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tdt.it.finalproject.jsondata.GoldPrice;

public class WriterGoldPrice {
	private String url;
	private String cssQuery;
	
	
	public WriterGoldPrice(String url, String cssQuery) {
		super();
		this.url = url;
		this.cssQuery = cssQuery;
	}
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
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
	 * @param cssQuery the cssQuery to set
	 */
	public void setCssQuery(String cssQuery) {
		this.cssQuery = cssQuery;
	}

	public ArrayList<String> exportDB() throws IOException{
		ArrayList<String> result = new ArrayList<String>();
		Document doc = Jsoup.connect(this.getUrl()).get();
		
		Elements aElements = doc.select(this.getCssQuery());
		for (Element aElement : aElements) {
			 result.add(aElement.text());
		}
		return  result;
	}
	public void getData() throws IOException{
		ArrayList<String> tmpExp = new ArrayList<String>();
		ArrayList<GoldPrice> rs     = new ArrayList<GoldPrice>();
		int count = 0;
		tmpExp = this.exportDB();
		for(int i = 0; i < tmpExp.size()-1; i++){
			count++;
			if(i != tmpExp.size()-2){
				GoldPrice js = new GoldPrice(count,tmpExp.get(i), Double.parseDouble(tmpExp.get(i+1).replace(",","")),Double.parseDouble(tmpExp.get(i+2).replace(",","")));
				rs.add(js);
				i+=2;
			}
			
		}
		String JSONResult = JSONArray.toJSONString(rs);
		System.out.println(JSONResult);
		try (FileWriter file = new FileWriter("json.txt")) {
			file.write(JSONResult);
			System.out.println("Successfully Copied JSON Object to File...");
		}
	}
	public static void main(String[] args) throws IOException {
		WriterGoldPrice jsoup = new WriterGoldPrice("http://vip.giavang.net/teline2/data/banggiavang1a.php", "div#myDiv table:gt(0) tbody tr:gt(1) td font");
		jsoup.getData();
	}
}
