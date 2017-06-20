package main.tdt.it.finalproject.scraper;

import java.util.ArrayList;

import main.tdt.it.finalproject.exception.ScraperException;
import main.tdt.it.finalproject.modal.InterestRatePrices;

public class Provision {
	public static void main(String[] args) {
		try {
			ContextDocumentInterestRate contextDocumentInterestRate = new ContextDocumentInterestRate(
					"http://vietbao.vn/vn/lai-suat-tiet-kiem/");
			contextDocumentInterestRate.setCssQuery(ContextDocument.CSS_QUERY_INTERESTRATE);
			InterestRateScraper interestRateScaper = new InterestRateScraper();
			ArrayList<String> elements = contextDocumentInterestRate.getElements();
			interestRateScaper.setElements(elements);

			InterestRatePrices irs = interestRateScaper.getDatas();
			irs.forEach(f -> System.out.println(f));
		} catch (ScraperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
