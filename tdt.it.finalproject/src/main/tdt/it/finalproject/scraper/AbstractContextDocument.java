package main.tdt.it.finalproject.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import main.tdt.it.finalproject.exception.ScraperException;

public abstract class AbstractContextDocument {

	public static final String CSS_QUERY_DOLLAR = "#ratetb tr:first-child td:first-child b,#ratetb tr:first-child td:last-child span.c4";
	public static final String CSS_QUERY_DOLLAR_BACKUP = "#exchangerate .tbl-exch tr:last-child td:first-child,#exchangerate .tbl-exch tr:last-child td:last-child";
	public static final String CSS_QUERY_GOLD_BACKUP = "#tbl tr:nth-child(7) td:first-child,#tbl tr:nth-child(7) td:nth-child(2),#tbl tr:nth-child(7) td:last-child";
	public static final String CSS_QUERY_GOLD = "#gold_tb #goldtb #SJCH_Ch_Minh td.c1 b,#gold_tb #goldtb #SJCH_Ch_Minh span.c2,#gold_tb #goldtb #SJCH_Ch_Minh span.c4";
	public static final String CSS_QUERY_INTERESTRATE = ".ruler1 tr:gt(0) td:nth-child(1),.ruler1 tr:gt(0) td:nth-child(2) img[src],.ruler1 tr:gt(0) td:nth-child(3)";
	public static final String CSS_QUERY_WORLDGOLD = "#wgold table tbody tr:nth-child(2) td div:nth-child(1) span, #wgold table tbody tr:nth-child(3) #2GROUPSPOT_GOLDSJC span.c2, #wgold table tbody tr:nth-child(3) #2GROUPSPOT_GOLDSJC span.c4";
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
