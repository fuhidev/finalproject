package test.tdt.it.finalproject.scraper;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

import main.tdt.it.finalproject.generateday.GenerateDay;
import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.scraper.MultiTyGiaScaper;

public class MultiTyGiaScaperTest {
	private MultiTyGiaScaper scraper = new MultiTyGiaScaper();

	@Test
	public void test() {
		List<String> dates = new GenerateDay().generate("20170310", "20170317");
		scraper.setDates(dates);
		List<AssetPrice> dollarData = scraper.getDollarData();
		Object[] expecteds = new DollarPrice[] { new DollarPrice(0, "USD", 22740, 22740, 22810, "20170316"),
				new DollarPrice(1, "USD", 22745, 22745, 22815, "20170317") };
		assertArrayEquals(expecteds, dollarData.toArray());
	}
}
