package main.tdt.it.finalproject;

import java.util.Iterator;
import java.util.List;

import main.tdt.it.finalproject.exception.ScraperException;
import main.tdt.it.finalproject.generateday.GenerateDay;
import main.tdt.it.finalproject.jdbc.preparedstatement.DollarDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.GoldDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.InterestRateDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.WorldGoldDatabase;
import main.tdt.it.finalproject.modal.AbstractPrice;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.modal.InterestRate;
import main.tdt.it.finalproject.modal.WorldGold;
import main.tdt.it.finalproject.scraper.ContextDocument;
import main.tdt.it.finalproject.scraper.ContextDocumentInterestRate;
import main.tdt.it.finalproject.scraper.DollarScraper;
import main.tdt.it.finalproject.scraper.GoldScraper;
import main.tdt.it.finalproject.scraper.InterestRateScraper;
import main.tdt.it.finalproject.scraper.WorldGoldScraper;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class MainRun {

	
	// private static final int MAX = 365 * 2 - 1;// ghi du lieu 2 nam 1 lan

	@SuppressWarnings("unchecked")
	public static void getWorldGoldScraper() {
		GenerateDay generateDay = new GenerateDay();
		List<String> lstDay = generateDay.generate(BEGIN, END);
		for (int i = 0; i < lstDay.size(); i++) {
			WorldGoldDatabase database = new WorldGoldDatabase();
			try {
				ContextDocument contextDocument = new ContextDocument(
						"https://www.tygia.com/?nganhang=VIETCOM&ngay=" + lstDay.get(i));
				contextDocument.setCssQuery(ContextDocument.CSS_QUERY_WORLDGOLD);
				WorldGoldScraper worldGoldScraper = new WorldGoldScraper();
				worldGoldScraper.setDate(lstDay.get(i));
				worldGoldScraper.setElements(contextDocument.getElements());
				List<? extends AbstractPrice> worldGold = worldGoldScraper.getDatas();
				database.adds((Iterator<WorldGold>) worldGold.iterator());
			} catch (ScraperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@SuppressWarnings("unchecked")
	public static void getGoldScraper() {
		GenerateDay generateDay = new GenerateDay();
		List<String> lstDay = generateDay.generate(BEGIN, END);
		for (int i = 0; i < lstDay.size(); i++) {
			GoldDatabase goldDatabase = new GoldDatabase();
			try {
				ContextDocument contextDocument = new ContextDocument(
						"https://www.tygia.com/?nganhang=VIETCOM&ngay=" + lstDay.get(i));
				contextDocument.setCssQuery(ContextDocument.CSS_QUERY_GOLD);
				GoldScraper goldScaper = new GoldScraper();
				goldScaper.setDate(lstDay.get(i));
				goldScaper.setElements(contextDocument.getElements());
				List<? extends AbstractPrice> golds = goldScaper.getDatas();
				goldDatabase.add((GoldPrice) golds.get(i));
			} catch (ScraperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@SuppressWarnings("unchecked")
	public static void getDollarScraper() {
		GenerateDay generateDay = new GenerateDay();
		List<String> lstDay = generateDay.generate(BEGIN, END);
		for (int i = 0; i < lstDay.size(); i++) {
			DollarDatabase dollarDatabase = new DollarDatabase();
			try {
				ContextDocument contextDocument = new ContextDocument(
						"https://www.tygia.com/?nganhang=VIETCOM&ngay=" + lstDay.get(i));
				contextDocument.setCssQuery(ContextDocument.CSS_QUERY_DOLLAR);
				DollarScraper dollarScaper = new DollarScraper();
				dollarScaper.setDate(lstDay.get(i));
				dollarScaper.setElements(contextDocument.getElements());
				List<? extends AbstractPrice> dollars = dollarScaper.getDatas();
				dollarDatabase.add((DollarPrice) dollars.get(i));
			} catch (ScraperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@SuppressWarnings("unchecked")
	public static void getScaperInDay() {
		try {
			// Get InterestRate at TIME NOW, vietbao.vn
			ContextDocumentInterestRate contextDocumentInterestRate = new ContextDocumentInterestRate(
					"http://vietbao.vn/vn/lai-suat-tiet-kiem/");
			contextDocumentInterestRate.setCssQuery(ContextDocument.CSS_QUERY_INTERESTRATE);
			InterestRateScraper interestRateScaper = new InterestRateScraper();
			interestRateScaper.setElements(contextDocumentInterestRate.getElements());
			InterestRateDatabase database = new InterestRateDatabase();
			List<? extends AbstractPrice> irs = interestRateScaper.getDatas();
			database.adds((Iterator<InterestRate>) irs.iterator());
		} catch (ScraperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static final String END = "20170718";
	private static final String BEGIN = "20170718";
	public static void main(String[] args) {
//		 getScaperInDay();
//		 getDollarScraper();
//		 getGoldScraper();
		getWorldGoldScraper();
	}
}
