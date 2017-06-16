package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.nodes.Element;

import main.tdt.it.finalproject.exception.ScraperException;

public interface IContextDocument {
	
	ArrayList<String> getElements() throws ScraperException;
	ArrayList<String> getElements(String cssQuery) throws ScraperException;
	Iterator<Element> getContextDocument() throws ScraperException;
	Iterator<Element> getContextDocument(String cssQuery) throws ScraperException;

}
