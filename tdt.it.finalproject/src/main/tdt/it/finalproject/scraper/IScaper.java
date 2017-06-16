package main.tdt.it.finalproject.scraper;

import java.util.List;

import main.tdt.it.finalproject.jsondata.AssetPrice;

public interface IScaper {

	void setElements(List<String> elements);
	
	
	List<AssetPrice> getDatas() throws Exception;
}
