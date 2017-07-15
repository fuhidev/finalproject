package test.tdt.it.finalproject.scraper;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.jsondata.DollarPrice1;
import main.tdt.it.finalproject.modal.AbstractPrice;
import main.tdt.it.finalproject.modal.GoldPrice;
import main.tdt.it.finalproject.scraper.TyGiaScaper;

public class TestTyGiaScraper {
	private TyGiaScaper scraper = new TyGiaScaper();
	@Before
	public void init(){
		scraper.setDate("20160101");
	}
	@Test
	public void testDollar() throws NotFoundAssetException {
		
		List<AbstractPrice> dollarData = scraper.getDollarData();
		Object[] expecteds = new DollarPrice1[]{new DollarPrice1(0, "USD", "22,745", "22,745", "22,815", "20170317")};
		assertEquals(expecteds.length,dollarData.size());
		assertArrayEquals(expecteds, dollarData.toArray());
	}
	@Test
	public void testGold() {
		 List<AbstractPrice> goldData;
		try {
			goldData = scraper.getGoldData();
			Object[] expecteds = new GoldPrice[]{};
			assertArrayEquals(expecteds, goldData.toArray());
		} catch (NotFoundAssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}