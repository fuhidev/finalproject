package main.tdt.it.finalproject.scraper;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ContextDocument implements IContextDocument{
	protected String URL;
	protected String cssQuery = null;
	protected Document document;
	public static final String CSS_QUERY_DOLLAR = "#ratetb tr:first-child td.c1 b,#ratetb tr:first-child td span.c2,#ratetb tr:first-child td span.c3, #ratetb tr:first-child td span.c4";
	public static final  String CSS_QUERY_GOLD = "#gold_tb #goldtb td.c1 b,#gold_tb #goldtb span.c2,#gold_tb #goldtb span.c4";
	public static final  String CSS_QUERY_INTERESTRATE = ".ruler1 tr:gt(0) td:nth-child(1),.ruler1 tr:gt(0) td:nth-child(2) img[src],.ruler1 tr:gt(0) td:nth-child(3)";
	
	


	public ContextDocument(String URL) {
		super();
		setURL(URL);
		try {
			this.document = Jsoup.connect(this.getURL()).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getCssQuery() {
		return cssQuery;
	}

	public void setCssQuery(String cssQuery) {
		this.cssQuery = cssQuery;
	}



	public ArrayList<String> getElements() {
		ArrayList<String> result = new ArrayList<String>();
		if ( this.document != null) {
			Elements aElements = this.document.select(cssQuery);
			for (Element aElement : aElements) {
				if(aElement.hasText()){
					result.add(aElement.text());
					
				}
			}
		}
		return result;

	}

}
