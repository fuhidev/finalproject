package main.tdt.it.finalproject.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.tdt.it.finalproject.exception.NullContextDocumentException;
import main.tdt.it.finalproject.exception.NullCssQueryException;
import main.tdt.it.finalproject.exception.ScraperException;

public class ContextDocument implements IContextDocument {
	protected String URL;
	protected String cssQuery = null;
	protected Document document;
	public static final String CSS_QUERY_DOLLAR = "#ratetb tr:first-child td.c1 b,#ratetb tr:first-child td span.c2,#ratetb tr:first-child td span.c3, #ratetb tr:first-child td span.c4";
	public static final String CSS_QUERY_GOLD = "#gold_tb #goldtb td.c1 b,#gold_tb #goldtb span.c2,#gold_tb #goldtb span.c4";
	public static final String CSS_QUERY_INTERESTRATE = ".ruler1 tr:gt(0) td:nth-child(1),.ruler1 tr:gt(0) td:nth-child(2) img[src],.ruler1 tr:gt(0) td:nth-child(3)";

	public ContextDocument(String URL) {
		super();
		try {
			setURL(URL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

	public ArrayList<String> getElements() throws ScraperException {
		return getElements(this.cssQuery);

	}

	public ArrayList<String> getElements(String cssQuery) throws ScraperException {
		ArrayList<String> result = new ArrayList<String>();
		Iterator<Element> aElements = getContextDocument(cssQuery);
		while (aElements.hasNext()) {
			Element aElement = aElements.next();
			if (aElement.hasText()) {
				result.add(aElement.text());

			}
		}

		return result;

	}

	@Override
	public Iterator<Element> getContextDocument() throws ScraperException {
		return this.getContextDocument(this.cssQuery);
	}

	@Override
	public Iterator<Element> getContextDocument(String cssQuery) throws ScraperException {
		if (cssQuery == null || cssQuery.isEmpty()) {
			throw new NullCssQueryException();
		}
		Elements aElements = null;
		if (this.document != null) {
			aElements = this.document.select(cssQuery);
		}
		if (aElements == null || aElements.size() == 0)
			throw new NullContextDocumentException(cssQuery);
		return aElements.iterator();
	}

}
