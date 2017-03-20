package main.tdt.it.finalproject;

import java.util.List;

import main.tdt.it.finalproject.generateDay.GenerateDay;
import main.tdt.it.finalproject.jsondata.service.WriterJson;
import main.tdt.it.finalproject.scraper.MultiTyGiaScaper;

public class MainRun {

	private static final String END = "20170320";
	private static final String BEGIN = "20170101";
	private static final int MAX = 365*2;

	public static void main(String[] args) {
		GenerateDay generateDay = new GenerateDay();
		List<String> lstDay = generateDay.generate(BEGIN, END);

		MultiTyGiaScaper multiTyGiaScaper = new MultiTyGiaScaper();

		for (int i = 0; i < lstDay.size(); i += MAX) {
			int max = MAX;
			if (max > lstDay.size() - i - 1)
				max = lstDay.size() - 1;
			List<String> days = lstDay.subList(i, i + max);
			multiTyGiaScaper.setDates(days);

			Thread t1 = new Thread(() -> {
				System.out.println(String.format("Duyet du lieu Dollar tu ngay %s den %s", days.get(0),
						days.get(days.size() - 1)));
				new WriterJson(String.format("dollar_%s-%s", days.get(0), days.get(days.size() - 1)))
						.export(multiTyGiaScaper.getDollarData());
				;
			});

			Thread t2 = new Thread(() -> {
				System.out.println(
						String.format("Duyet du lieu Gold tu ngay %s den %s", days.get(0), days.get(days.size() - 1)));
				new WriterJson(String.format("gold_%s-%s", days.get(0), days.get(days.size() - 1)))
						.export(multiTyGiaScaper.getGoldData());
				;
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
