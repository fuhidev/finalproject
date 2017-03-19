package main.tdt.it.finalproject;

import java.util.List;

import main.tdt.it.finalproject.generateDay.GenerateDay;
import main.tdt.it.finalproject.jsondata.service.WriterJson;
import main.tdt.it.finalproject.scraper.MultiTyGiaScaper;

public class MainRun {
	public static void main(String[] args) {
		GenerateDay generateDay = new GenerateDay();
		List<String> lstDay = generateDay.generate("20130101", "20171803");

		MultiTyGiaScaper multiTyGiaScaper = new MultiTyGiaScaper();

		for (int i = 0; i < lstDay.size(); i += 999) {
			int max = 999;
			if (max > lstDay.size() - i - 1)
				max = lstDay.size() - 1;
			List<String> days = lstDay.subList(i, max);
			multiTyGiaScaper.setDates(days);

			Thread t1 = new Thread(() -> {
				System.out.println(String.format("dollar %s-%s", days.get(0), days.get(days.size() - 1)));
				new WriterJson(String.format("dollar %s-%s", days.get(0), days.get(days.size() - 1)))
						.export(multiTyGiaScaper.getDollarData());
				;
			});

			Thread t2 = new Thread(() -> {
				System.out.println(String.format("gold %s-%s", days.get(0), days.get(days.size() - 1)));
				new WriterJson(String.format("gold %s-%s", days.get(0), days.get(days.size() - 1)))
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
