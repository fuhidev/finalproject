package test.tdt.it.finalproject.jsondata.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.jsondata.service.SummaryDollar;

public class SummaryAssetTest {

	@Test
	public void testDollar() {
		SummaryDollar dollar = new SummaryDollar();
		List<AssetPrice> datas =new ArrayList<>();
		datas.add(new DollarPrice(1, "1", 1000, 1000, 1, "10102011"));
		dollar.setDatas(datas );
	}

}
