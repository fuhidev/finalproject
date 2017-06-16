package main.tdt.it.finalproject;

import java.util.List;

import main.tdt.it.finalproject.exception.ScraperException;
import main.tdt.it.finalproject.generateday.GenerateDay;
import main.tdt.it.finalproject.jdbc.preparedstatement.Gold_DollarPreparedStatement;
import main.tdt.it.finalproject.jsondata.WorldGold;
import main.tdt.it.finalproject.modal.AbstractPrice;
import main.tdt.it.finalproject.modal.DollarPrice;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.modal.InterestRatePrices;
import main.tdt.it.finalproject.scraper.ContextDocument;
import main.tdt.it.finalproject.scraper.ContextDocumentInterestRate;
import main.tdt.it.finalproject.scraper.DollarScraper;
import main.tdt.it.finalproject.scraper.GoldScraper;
import main.tdt.it.finalproject.scraper.InterestRateScraper;
import main.tdt.it.finalproject.scraper.WorldGoldScraper;

public class MainRun {
	private static final String END = "20170616";
	private static final String BEGIN = "20170610";
//	private static final int MAX = 365 * 2 - 1;// ghi du lieu 2 nam 1 lan
	
	@SuppressWarnings("unchecked")
	public static void getWorldGoldScraper() {
		GenerateDay generateDay = new GenerateDay();
		List<String> lstDay = generateDay.generate(BEGIN, END);
		for (int i = 0; i < lstDay.size(); i++) {
			Gold_DollarPreparedStatement statement = new Gold_DollarPreparedStatement();
			try {
				ContextDocument contextDocument = new ContextDocument(
						"https://www.tygia.com/?nganhang=VIETCOM&ngay=" + lstDay.get(i));
				contextDocument.setCssQuery(ContextDocument.CSS_QUERY_WORLDGOLD);
				WorldGoldScraper worldGoldScraper = new WorldGoldScraper();
				worldGoldScraper.setDate(lstDay.get(i));
				worldGoldScraper.setElements(contextDocument.getElements());
				List<? extends AbstractPrice> worldGold = worldGoldScraper.getDatas();
				statement.addWorldGold((List<WorldGold>) worldGold);
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
			Gold_DollarPreparedStatement statement = new Gold_DollarPreparedStatement();
			try {
				ContextDocument contextDocument = new ContextDocument(
						"https://www.tygia.com/?nganhang=VIETCOM&ngay=" + lstDay.get(i));
				contextDocument.setCssQuery(ContextDocument.CSS_QUERY_GOLD);
				GoldScraper goldScaper = new GoldScraper();
				goldScaper.setDate(lstDay.get(i));
				goldScaper.setElements(contextDocument.getElements());
				List<? extends AbstractPrice> golds = goldScaper.getDatas();
				statement.addVNGold((List<GoldPrice>) golds);
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
			Gold_DollarPreparedStatement statement = new Gold_DollarPreparedStatement();
			try {
				ContextDocument contextDocument = new ContextDocument(
						"https://www.tygia.com/?nganhang=VIETCOM&ngay=" + lstDay.get(i));
				contextDocument.setCssQuery(ContextDocument.CSS_QUERY_DOLLAR);
				DollarScraper dollarScaper = new DollarScraper();
				dollarScaper.setDate(lstDay.get(i));
				dollarScaper.setElements(contextDocument.getElements());
				List<? extends AbstractPrice> dollar = dollarScaper.getDatas();
				statement.addDollar((List<DollarPrice>) dollar);
			} catch (ScraperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
	}
	
	public static void getScaperInDay() {
		try {
		//Get InterestRate at TIME NOW, vietbao.vn
		ContextDocumentInterestRate contextDocumentInterestRate = new ContextDocumentInterestRate("http://vietbao.vn/vn/lai-suat-tiet-kiem/");
		contextDocumentInterestRate.setCssQuery(ContextDocument.CSS_QUERY_INTERESTRATE);
		InterestRateScraper interestRateScaper = new InterestRateScraper();
		interestRateScaper.setElements(contextDocumentInterestRate.getElements());
		
			InterestRatePrices irs = interestRateScaper.getDatas();
			irs.forEach(f-> System.out.println(f));
		} catch (ScraperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
//		getScaperInDay();
//		getDollarScraper();
		getWorldGoldScraper();
	}
}
