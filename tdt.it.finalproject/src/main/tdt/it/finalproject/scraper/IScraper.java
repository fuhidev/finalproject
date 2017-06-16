package main.tdt.it.finalproject.scraper;

import java.util.List;

import main.tdt.it.finalproject.modal.AbstractPrice;

public interface IScraper {

	void setElements(List<String> elements);
	
	
	List<AbstractPrice> getDatas() throws Exception;
}
