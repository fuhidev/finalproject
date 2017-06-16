package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.modal.AbstractPrice;

public interface IScraper {

	void setElements(List<String> elements);
	
	
	ArrayList<? extends AbstractPrice> getDatas() throws Exception;
}
