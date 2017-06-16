package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.modal.AbstractPrice;

public interface IScraper<T extends ArrayList<? extends AbstractPrice>> {

	void setElements(List<String> elements);
	
	
	T getDatas() throws Exception;
}
