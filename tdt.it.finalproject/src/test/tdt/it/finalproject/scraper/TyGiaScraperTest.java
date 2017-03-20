package test.tdt.it.finalproject.scraper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.jsondata.GoldPrice;
import main.tdt.it.finalproject.scraper.TyGiaScaper;

public class TyGiaScraperTest {
	private TyGiaScaper scraper = new TyGiaScaper();
	@Before
	public void init(){
		scraper.setDate("20170101");
	}
	@Test
	public void testDollar() {
		
		List<AssetPrice> dollarData = scraper.getDollarData();
		Object[] expecteds = new DollarPrice[]{new DollarPrice(0, "USD", "22,745", "22,745", "22,815", "20170317")};
		assertEquals(expecteds.length,dollarData.size());
		assertArrayEquals(expecteds, dollarData.toArray());
	}
//	@Test
//	public void testGold() {
//		 List<AssetPrice> goldData = scraper.getGoldData();
//		Object[] expecteds = new GoldPrice[]{};
//		assertArrayEquals(expecteds, goldData.toArray());
//		for(AssetPrice g : goldData){
//			System.out.println(g);
//		}
//	}

}
