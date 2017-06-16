package main.tdt.it.finalproject.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import main.tdt.it.finalproject.exception.ScraperException;

public abstract class AbstractContextDocument {

	public static final String CSS_QUERY_DOLLAR = "#ratetb tr:first-child td.c1 b,#ratetb tr:first-child td span.c2,#ratetb tr:first-child td span.c3, #ratetb tr:first-child td span.c4";
	public static final String CSS_QUERY_GOLD = "#gold_tb #goldtb td.c1 b,#gold_tb #goldtb span.c2,#gold_tb #goldtb span.c4";
	public static final String CSS_QUERY_INTERESTRATE = ".ruler1 tr:gt(0) td:nth-child(1),.ruler1 tr:gt(0) td:nth-child(2) img[src],.ruler1 tr:gt(0) td:nth-child(3)";

	protected String URL;
	protected String cssQuery = null;
	protected Document document;
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) throws IOException {
		URL = uRL;
		this.document = Jsoup.connect(this.getURL()).get();
	}

	public String getCssQuery() {
		return cssQuery;
	}

	public void setCssQuery(String cssQuery) {
		this.cssQuery = cssQuery;
	}
	public ArrayList<String> getElements() throws ScraperException{
		return this.getElements(this.cssQuery);
	};
	public abstract ArrayList<String> getElements(String cssQuery) throws ScraperException;
	public Iterator<Element> getContextDocument() throws ScraperException{
		return this.getContextDocument(this.cssQuery);
	};
	public abstract Iterator<Element> getContextDocument(String cssQuery) throws ScraperException;
}
