package main.tdt.it.finalproject;

import java.util.List;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.generateday.GenerateDay;
import main.tdt.it.finalproject.jdbc.preparedstatement.Gold_DollarPreparedStatement;
import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.ExcelFile;
import main.tdt.it.finalproject.jsondata.GoldPrice;
import main.tdt.it.finalproject.scraper.ContextDocument;
import main.tdt.it.finalproject.scraper.ContextDocumentInterestRate;
import main.tdt.it.finalproject.scraper.DollarScaper;
import main.tdt.it.finalproject.scraper.GoldScaper;
import main.tdt.it.finalproject.scraper.InterestRateScaper;

public class MainRun {
	private static final String END = "20170616";
	private static final String BEGIN = "20170609";
	// private static final int MAX = 365 * 2 - 1;// ghi du lieu 2 nam 1 lan

	// public static void main(String[] args) {

	// System.out.println(lstDay.size());// á»� 2 thÃ¡ng ná»¯a
	//
	// MultiTyGiaScaper multiTyGiaScaper = new MultiTyGiaScaper();
	// int max = MAX;// ban dau gan max cho const
	// // step = i+=max + 1, vi du sublist tu 0 den 9, sau do sublist tu 10 den
	// // 19 (MAX=10)
	// for (int i = 0; i < lstDay.size(); i += max + 1) {
	// // kiem tra xem i+max > so ngay hay khong, vi du lstDay co 21 phan
	// // tu, i = 20, max = 10 i+ max > 21 nen sublist tu 20 den 21
	// System.out.println(String.format("Duyet du lieu tu %s den %s", i,
	// (i + max > lstDay.size() - 1 ? (lstDay.size()) : i + max)));
	// List<String> days = lstDay.subList(i, i + max > lstDay.size() - 1 ?
	// (lstDay.size()) : i + max + 1);
	// System.out.println(
	// String.format("Duyet du lieu Dollar tu ngay %s den %s", days.get(0),
	// days.get(days.size() - 1)));
	// multiTyGiaScaper.setDates(days);
	//
	// Thread t1 = new Thread(() -> {
	// System.out.println(String.format("Duyet du lieu Dollar tu ngay %s den
	// %s", days.get(0),
	// days.get(days.size() - 1)));
	// List<AssetPrice> dollarData = multiTyGiaScaper.getDollarData();
	// if (dollarData != null && dollarData.size() > 0) {
	// new WriterJson(String.format("dollar_%s-%s", days.get(0),
	// days.get(days.size() - 1)))
	// .export(dollarData);
	// } else {
	// System.err.println(String.format("Khong tim thay du lieu dollar tu ngay
	// %s den %s", days.get(0),
	// days.get(days.size() - 1)));
	// }
	//
	// });
	//
	// Thread t2 = new Thread(() -> {
	// System.out.println(
	// String.format("Duyet du lieu Gold tu ngay %s den %s", days.get(0),
	// days.get(days.size() - 1)));
	// List<AssetPrice> goldData = multiTyGiaScaper.getGoldData();
	// if (goldData != null && goldData.size() > 0)
	// new WriterJson(String.format("gold_%s-%s", days.get(0),
	// days.get(days.size() - 1)))
	// .export(goldData);
	// else {
	// System.err.println(String.format("Khong tim thay du lieu gold tu ngay %s
	// den %s", days.get(0),
	// days.get(days.size() - 1)));
	// }
	// });
	//
	// try {
	// t1.start();
	// t2.start();
	// t1.join();
	// t2.join();
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	// }
	// public static void main(String[] args) {
	// GenerateDay generateDay = new GenerateDay();
	// List<String> lstDay = generateDay.generate(BEGIN, END);
	// System.out.println(lstDay.size());// á»� 2 thÃ¡ng ná»¯a
	//
	// MultiTyGiaScaper multiTyGiaScaper = new MultiTyGiaScaper();
	// int max = MAX;// ban dau gan max cho const
	// // step = i+=max + 1, vi du sublist tu 0 den 9, sau do sublist tu 10 den
	// // 19 (MAX=10)
	// for (int i = 0; i < lstDay.size(); i += max + 1) {
	// // kiem tra xem i+max > so ngay hay khong, vi du lstDay co 21 phan
	// // tu, i = 20, max = 10 i+ max > 21 nen sublist tu 20 den 21
	// System.out.println(String.format("Duyet du lieu tu %s den %s", i,
	// (i + max > lstDay.size() - 1 ? (lstDay.size()) : i + max)));
	// List<String> days = lstDay.subList(i, i + max > lstDay.size() - 1 ?
	// (lstDay.size()) : i + max + 1);
	// multiTyGiaScaper.setDates(days);
	//
	// List<AssetPrice> golds = new ArrayList<>();
	// List<AssetPrice> dollars = new ArrayList<>();
	//
	// multiTyGiaScaper.getData(golds, dollars);
	//
	// Thread t1 = new Thread(() -> {
	// if (dollars != null && dollars.size() > 0) {
	// new WriterJson(String.format("dollar_%s-%s", days.get(0),
	// days.get(days.size() - 1)))
	// .export(dollars);
	//
	// } else {
	// System.err.println(String.format("Khong tim thay du lieu dollar tu ngay
	// %s den %s", days.get(0),
	// days.get(days.size() - 1)));
	// }
	//
	// });
	//
	// Thread t2 = new Thread(() -> {
	// if (golds != null && golds.size() > 0)
	// new WriterJson(String.format("gold_%s-%s", days.get(0),
	// days.get(days.size() - 1)))
	// .export(golds);
	// else {
	// System.err.println(String.format("Khong tim thay du lieu gold tu ngay %s
	// den %s", days.get(0),
	// days.get(days.size() - 1)));
	// }
	// });
	//
	// try {
	// t1.start();
	// t2.start();
	// t1.join();
	// t2.join();
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	// }

	// public static void main(String[] args) {
	// // Insert gold data to mysql
	//
	// GenerateDay generateDay = new GenerateDay();
	// List<String> lstDay = generateDay.generate(BEGIN, END);
	// System.out.println(lstDay.size());// á»� 2 thÃ¡ng ná»¯a
	//
	// MultiTyGiaScaper multiTyGiaScaper = new MultiTyGiaScaper();
	// int max = MAX;// ban dau gan max cho const
	// // step = i+=max + 1, vi du sublist tu 0 den 9, sau do sublist tu 10 den
	// // 19 (MAX=10)
	//// Thread t3 = new Thread(()->{
	//// Gold_DollarPreparedStatement statement = new
	// Gold_DollarPreparedStatement();
	//// ExcelFile worldGolds = new ExcelFile();
	//// statement.addWorldGold(worldGolds.readFileFromExcel("C:/Users/TIEN/Desktop/Data.xls"));
	//// });
	//// t3.start();
	//
	// for (int i = 0; i < lstDay.size(); i += max + 1) {
	// // kiem tra xem i+max > so ngay hay khong, vi du lstDay co 21 phan
	// // tu, i = 20, max = 10 i+ max > 21 nen sublist tu 20 den 21
	// System.out.println(String.format("Duyet du lieu tu %s den %s", i,
	// (i + max > lstDay.size() - 1 ? (lstDay.size()) : i + max)));
	// List<String> days = lstDay.subList(i, i + max > lstDay.size() - 1 ?
	// (lstDay.size()) : i + max + 1);
	// multiTyGiaScaper.setDates(days);
	//
	// List<AssetPrice> golds = new ArrayList<>();
	// List<AssetPrice> dollars = new ArrayList<>();
	//
	// multiTyGiaScaper.getData(golds, dollars);
	//
	// Thread t1 = new Thread(() -> {
	// if (dollars != null && dollars.size() > 0) {
	// new WriterJson(String.format("dollar_%s-%s", days.get(0),
	// days.get(days.size() - 1)))
	// .export(dollars);
	//
	// } else {
	// System.err.println(String.format("Khong tim thay du lieu dollar tu ngay
	// %s den %s", days.get(0),
	// days.get(days.size() - 1)));
	// }
	//
	// });
	//
	// Thread t2 = new Thread(() -> {
	// if (golds != null && golds.size() > 0)
	// new WriterJson(String.format("gold_%s-%s", days.get(0),
	// days.get(days.size() - 1)))
	// .export(golds);
	// else {
	// System.err.println(String.format("Khong tim thay du lieu gold tu ngay %s
	// den %s", days.get(0),
	// days.get(days.size() - 1)));
	// }
	// });
	//
	// try {
	// t1.start();
	// t2.start();
	//
	// t1.join();
	// t2.join();
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

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
				GoldScaper goldScaper = new GoldScaper();
				goldScaper.setDate(lstDay.get(i));
				goldScaper.setElements(contextDocument.getElements());
				List<? extends AssetPrice> golds = goldScaper.getDatas();
				statement.addVNGold((List<GoldPrice>) golds);
			} catch (NotFoundAssetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
	}

	public static void getScaperInDay() {
		// Get Gold and dollar at 01/01/2016, tygia.com
		String date = "20160101";
		ContextDocument contextDocument = new ContextDocument("https://www.tygia.com/?nganhang=VIETCOM&ngay=" + date);

		try {
//			// get gold
//			contextDocument.setCssQuery(ContextDocument.CSS_QUERY_GOLD);
//			GoldScaper goldScaper = new GoldScaper();
//			goldScaper.setDate(date);
//			goldScaper.setElements(contextDocument.getElements());
//			List<AssetPrice> golds = goldScaper.getDatas();
//			golds.forEach(f -> System.out.println(f));
			// get dollar
			contextDocument.setCssQuery(ContextDocument.CSS_QUERY_DOLLAR);
			DollarScaper dollarScaper = new DollarScaper();
			dollarScaper.setDate(date);
			dollarScaper.setElements(contextDocument.getElements());
			List<AssetPrice> dollars = dollarScaper.getDatas();
			dollars.forEach(f -> System.out.println(f));

		} catch (NotFoundAssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get InterestRate at TIME NOW, vietbao.vn
		ContextDocumentInterestRate contextDocumentInterestRate = new ContextDocumentInterestRate(
				"http://vietbao.vn/vn/lai-suat-tiet-kiem/");
		contextDocumentInterestRate.setCssQuery(ContextDocument.CSS_QUERY_INTERESTRATE);
		InterestRateScaper interestRateScaper = new InterestRateScaper();
		interestRateScaper.setElements(contextDocumentInterestRate.getElements());
		try {
			List<AssetPrice> irs = interestRateScaper.getDatas();
			irs.forEach(f -> System.out.println(f));
		} catch (NotFoundAssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		getGoldScraper();
		// getScaperInDay();
//		Gold_DollarPreparedStatement statement = new Gold_DollarPreparedStatement();
//		ExcelFile file = new ExcelFile();
//		statement.addDollarFormWorldGold(file.readFileFromExcel(
//				"C:/Users/Ngoc Tien/OneDrive/Luan Van/finalproject/tdt.it.finalproject/Database/Data.xls"));
	}
}
