package main.tdt.it.finalproject.scraper;

import java.util.List;

import main.tdt.it.finalproject.modal.AbstractPrice;

public interface IScraper<T extends List<? extends AbstractPrice>> {

	void setElements(List<String> elements);
	
	
	T getData() throws Exception;
}
