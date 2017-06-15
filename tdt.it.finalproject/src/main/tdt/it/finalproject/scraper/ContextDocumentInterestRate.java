package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ContextDocumentInterestRate extends ContextDocument{

	public ContextDocumentInterestRate(String URL) {
		super(URL);
		// TODO Auto-generated constructor stub
	}
	@Override
	public ArrayList<String> getElements() {
		ArrayList<String> result = new ArrayList<String>();
		if ( this.document != null) {
			Elements aElements = this.document.select(cssQuery);
			for (Element aElement : aElements) {
				if(aElement.hasText()){
					result.add(aElement.text());
					
				}
				result.add(aElement.attr("src"));
			}
		}
		return result;
	}

}
