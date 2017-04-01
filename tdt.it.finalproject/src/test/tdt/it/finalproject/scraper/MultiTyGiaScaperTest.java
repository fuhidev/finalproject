package test.tdt.it.finalproject.scraper;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Test;

import main.tdt.it.finalproject.generateDay.GenerateDay;
import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.scraper.MultiTyGiaScaper;

public class MultiTyGiaScaperTest {
	private MultiTyGiaScaper scraper = new MultiTyGiaScaper();

	@Test
	public void test() {
		List<String> dates = new GenerateDay().generate("20170316", "20170317");
		scraper.setDates(dates);
		List<AssetPrice> dollarData = scraper.getDollarData();
		Object[] expecteds = new DollarPrice[] { new DollarPrice(0, "USD", "22,740", "22,740", "22,810", "20170316"),
				new DollarPrice(1, "USD", "22,745", "22,745", "22,815", "20170317") };
		assertArrayEquals(expecteds, dollarData.toArray());
	}
}
