package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.nodes.Element;

import main.tdt.it.finalproject.exception.ScraperException;

public class ContextDocumentInterestRate extends ContextDocument {

	public ContextDocumentInterestRate(String URL) {
		super(URL);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<String> getElements(String cssQuery) throws ScraperException {
		ArrayList<String> result = new ArrayList<String>();
		Iterator<Element> aElements = getContextDocument(cssQuery);
		while (aElements.hasNext()) {
			Element aElement = aElements.next();
				if (aElement.hasText())
					result.add(aElement.text());

				if (aElement.hasAttr("src"))
					result.add(aElement.attr("src"));
		}
		return result;
	}

}
