package main.tdt.it.finalproject;

import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.generateday.GenerateDay;
import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.service.WriterJson;
import main.tdt.it.finalproject.scraper.MultiTyGiaScaper;

public class MainRun {

	private static final String END = "20150201";
	private static final String BEGIN = "20150101";
	private static final int MAX = 365 * 2 - 1;// ghi du lieu 2 nam 1 lan

//	public static void main(String[] args) {
//		GenerateDay generateDay = new GenerateDay();
//		List<String> lstDay = generateDay.generate(BEGIN, END);
//		System.out.println(lstDay.size());// á»� 2 thÃ¡ng ná»¯a
//
//		MultiTyGiaScaper multiTyGiaScaper = new MultiTyGiaScaper();
//		int max = MAX;// ban dau gan max cho const
//		// step = i+=max + 1, vi du sublist tu 0 den 9, sau do sublist tu 10 den
//		// 19 (MAX=10)
//		for (int i = 0; i < lstDay.size(); i += max + 1) {
//			// kiem tra xem i+max > so ngay hay khong, vi du lstDay co 21 phan
//			// tu, i = 20, max = 10 i+ max > 21 nen sublist tu 20 den 21
//			System.out.println(String.format("Duyet du lieu tu %s den %s", i,
//					(i + max > lstDay.size() - 1 ? (lstDay.size()) : i + max)));
//			List<String> days = lstDay.subList(i, i + max > lstDay.size() - 1 ? (lstDay.size()) : i + max + 1);
//			System.out.println(
//					String.format("Duyet du lieu Dollar tu ngay %s den %s", days.get(0), days.get(days.size() - 1)));
//			multiTyGiaScaper.setDates(days);
//
//			Thread t1 = new Thread(() -> {
//				System.out.println(String.format("Duyet du lieu Dollar tu ngay %s den %s", days.get(0),
//						days.get(days.size() - 1)));
//				List<AssetPrice> dollarData = multiTyGiaScaper.getDollarData();
//				if (dollarData != null && dollarData.size() > 0) {
//					new WriterJson(String.format("dollar_%s-%s", days.get(0), days.get(days.size() - 1)))
//							.export(dollarData);
//				} else {
//					System.err.println(String.format("Khong tim thay du lieu dollar tu ngay %s den %s", days.get(0),
//							days.get(days.size() - 1)));
//				}
//
//			});
//
//			Thread t2 = new Thread(() -> {
//				System.out.println(
//						String.format("Duyet du lieu Gold tu ngay %s den %s", days.get(0), days.get(days.size() - 1)));
//				List<AssetPrice> goldData = multiTyGiaScaper.getGoldData();
//				if (goldData != null && goldData.size() > 0)
//					new WriterJson(String.format("gold_%s-%s", days.get(0), days.get(days.size() - 1)))
//							.export(goldData);
//				else {
//					System.err.println(String.format("Khong tim thay du lieu gold tu ngay %s den %s", days.get(0),
//							days.get(days.size() - 1)));
//				}
//			});
//
//			try {
//				t1.start();
//				t2.start();
//				t1.join();
//				t2.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//	}
	public static void main(String[] args) {
		GenerateDay generateDay = new GenerateDay();
		List<String> lstDay = generateDay.generate(BEGIN, END);
		System.out.println(lstDay.size());// á»� 2 thÃ¡ng ná»¯a

		MultiTyGiaScaper multiTyGiaScaper = new MultiTyGiaScaper();
		int max = MAX;// ban dau gan max cho const
		// step = i+=max + 1, vi du sublist tu 0 den 9, sau do sublist tu 10 den
		// 19 (MAX=10)
		for (int i = 0; i < lstDay.size(); i += max + 1) {
			// kiem tra xem i+max > so ngay hay khong, vi du lstDay co 21 phan
			// tu, i = 20, max = 10 i+ max > 21 nen sublist tu 20 den 21
			System.out.println(String.format("Duyet du lieu tu %s den %s", i,
					(i + max > lstDay.size() - 1 ? (lstDay.size()) : i + max)));
			List<String> days = lstDay.subList(i, i + max > lstDay.size() - 1 ? (lstDay.size()) : i + max + 1);
			multiTyGiaScaper.setDates(days);
			
			List<AssetPrice> golds = new ArrayList<>();
			List<AssetPrice> dollars = new ArrayList<>();
			
			multiTyGiaScaper.getData(golds, dollars);

			Thread t1 = new Thread(() -> {
				if (dollars != null && dollars.size() > 0) {
					new WriterJson(String.format("dollar_%s-%s", days.get(0), days.get(days.size() - 1)))
					.export(dollars);

				} else {
					System.err.println(String.format("Khong tim thay du lieu dollar tu ngay %s den %s", days.get(0),
							days.get(days.size() - 1)));
				}

			});

			Thread t2 = new Thread(() -> {
				if (golds != null && golds.size() > 0)
					new WriterJson(String.format("gold_%s-%s", days.get(0), days.get(days.size() - 1)))
							.export(golds);
				else {
					System.err.println(String.format("Khong tim thay du lieu gold tu ngay %s den %s", days.get(0),
							days.get(days.size() - 1)));
				}
			});

			try {
				t1.start();
				t2.start();
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
