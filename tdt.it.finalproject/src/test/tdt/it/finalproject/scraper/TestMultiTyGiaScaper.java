package test.tdt.it.finalproject.scraper;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

import main.tdt.it.finalproject.generateday.GenerateDay;
import main.tdt.it.finalproject.jsondata.DollarPrice1;
import main.tdt.it.finalproject.modal.AbstractPrice;
import main.tdt.it.finalproject.scraper.MultiTyGiaScaper;

public class TestMultiTyGiaScaper {
	private MultiTyGiaScaper scraper = new MultiTyGiaScaper();

	@Test
	public void test() {
		List<String> dates = new GenerateDay().generate("20170310", "20170317");
		scraper.setDates(dates);
		List<AbstractPrice> dollarData = scraper.getDollarData();
		Object[] expecteds = new DollarPrice1[] { new DollarPrice1(0, "USD", 22740, 22740, 22810, "20170316"),
				new DollarPrice1(1, "USD", 22745, 22745, 22815, "20170317") };
		assertArrayEquals(expecteds, dollarData.toArray());
	}
}
