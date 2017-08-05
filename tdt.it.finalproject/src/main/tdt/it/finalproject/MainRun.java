package main.tdt.it.finalproject;

import java.util.Iterator;
import java.util.List;

import main.tdt.it.finalproject.exception.ScraperException;
import main.tdt.it.finalproject.generateday.GenerateDay;
import main.tdt.it.finalproject.jdbc.preparedstatement.DollarDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.GoldDatabase;
import main.tdt.it.finalproject.jdbc.preparedstatement.InterestRateDatabase;
import main.tdt.it.finalproject.modal.AbstractPrice;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.modal.InterestRate;
import main.tdt.it.finalproject.scraper.ContextDocument;
import main.tdt.it.finalproject.scraper.ContextDocumentInterestRate;
import main.tdt.it.finalproject.scraper.DollarScraper;
import main.tdt.it.finalproject.scraper.GoldScraper;
import main.tdt.it.finalproject.scraper.InterestRateScraper;
import main.tdt.it.finalproject.util.DateTimeUtil;

public class MainRun {

	
	public static void getGoldScraper() {
		GenerateDay generateDay = new GenerateDay();
		List<String> lstDay = generateDay.generate(BEGIN, END);
		for (int i = 0; i < lstDay.size(); i++) {
			GoldDatabase goldDatabase = new GoldDatabase();
			try {
				ContextDocument contextDocument = new ContextDocument(
						"https://www.tygia.com/" + lstDay.get(i));
				contextDocument.setCssQuery(ContextDocument.CSS_QUERY_GOLD);
				GoldScraper goldScaper = new GoldScraper();
				goldScaper.setDate(DateTimeUtil.formatStringToDate(lstDay.get(i)));
				goldScaper.setElements(contextDocument.getElements());
				List<? extends AbstractPrice> golds = goldScaper.getData();
				goldDatabase.add((GoldPrice) golds.get(i));
			} catch (ScraperException e) {
				getGoldBackupScraper(lstDay, goldDatabase, i);
			}

		}
	}

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
				dollarScaper.setDate(DateTimeUtil.formatStringToDate(lstDay.get(i)));
				dollarScaper.setElements(contextDocument.getElements());
				List<? extends AbstractPrice> dollars = dollarScaper.getData();
				dollarDatabase.add((DollarPrice) dollars.get(i));
				
			} catch (ScraperException e) {
				getDollarBackupScraper(lstDay,dollarDatabase,i);
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
			List<? extends AbstractPrice> irs = interestRateScaper.getData();
			database.adds((Iterator<InterestRate>) irs.iterator());
		} catch (ScraperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void getDollarBackupScraper(List<String> lstDay, DollarDatabase dollarDatabase, int i) {
		try {
			ContextDocument contextDocument = new ContextDocument(
					"https://vietcombank.com.vn/");
			contextDocument.setCssQuery(ContextDocument.CSS_QUERY_DOLLAR_BACKUP);
			DollarScraper dollarScaper = new DollarScraper();
//			dollarScaper.setDate(lstDay.get(i));
			dollarScaper.setElements(contextDocument.getElements());
			List<? extends AbstractPrice> dollars;
			dollars = dollarScaper.getData();
			dollarDatabase.add((DollarPrice) dollars.get(i));
		} catch (ScraperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void getGoldBackupScraper(List<String> lstDay, GoldDatabase goldDatabase, int i) {
		try {
			ContextDocument contextDocument = new ContextDocument(
					"http://banggia.giavang.net/");
			contextDocument.setCssQuery(ContextDocument.CSS_QUERY_GOLD_BACKUP);
			GoldScraper goldScaper = new GoldScraper();
//			goldScaper.setDate(lstDay.get(i));
			goldScaper.setElements(contextDocument.getElements());
			List<? extends AbstractPrice> golds = goldScaper.getData();
			goldDatabase.add((GoldPrice) golds.get(i));
		} catch (ScraperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static final String END = "20170805";
	private static final String BEGIN = "20170805";
	public static void main(String[] args) {
//		 getScaperInDay();
//		 getDollarScraper();
		 getGoldScraper();
//		getWorldGoldScraper();
	}
}
