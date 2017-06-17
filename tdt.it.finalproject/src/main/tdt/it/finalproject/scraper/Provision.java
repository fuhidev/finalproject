package main.tdt.it.finalproject.scraper;

import main.tdt.it.finalproject.exception.ScraperException;
import main.tdt.it.finalproject.modal.InterestRatePrices;

public class Provision {
	public static void main(String[] args) {
		try {
			ContextDocumentInterestRate contextDocumentInterestRate = new ContextDocumentInterestRate(
					"http://vietbao.vn/vn/lai-suat-tiet-kiem/");
			contextDocumentInterestRate.setCssQuery(ContextDocument.CSS_QUERY_INTERESTRATE);
			InterestRateScraper interestRateScaper = new InterestRateScraper();
			interestRateScaper.setElements(contextDocumentInterestRate.getElements());

			InterestRatePrices irs = interestRateScaper.getDatas();
			irs.forEach(f -> System.out.println(f));
		} catch (ScraperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
