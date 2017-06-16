package main.tdt.it.finalproject.scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.tdt.it.finalproject.exception.NullContextDocumentException;
import main.tdt.it.finalproject.exception.NullCssQueryException;
import main.tdt.it.finalproject.exception.ScraperException;

public class ContextDocument extends AbstractContextDocument {

	public ContextDocument(String URL) {
		super();
		try {
			setURL(URL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> getElements() throws ScraperException {
		return getElements(this.cssQuery);

	}

	@Override
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
