package test.tdt.it.finalproject.scraper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.scraper.TyGiaScaper;

public class TyGiaScraperTest {
	private TyGiaScaper scraper = new TyGiaScaper();
	@Test
	public void test() {
		scraper.setDate("20170317");
		List<AssetPrice> dollarData = scraper.getDollarData();
		Object[] expecteds = new DollarPrice[]{new DollarPrice(0, "USD", "22,745", "22,745", "22,815", "20170317")};
		assertEquals(expecteds.length,dollarData.size());
		assertArrayEquals(expecteds, dollarData.toArray());
		
	}

}
