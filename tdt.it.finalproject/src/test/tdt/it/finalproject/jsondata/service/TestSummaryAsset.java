package test.tdt.it.finalproject.jsondata.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.tdt.it.finalproject.jsondata.DollarPrice1;
import main.tdt.it.finalproject.jsondata.service.SummaryDollar;
import main.tdt.it.finalproject.modal.AbstractPrice;

public class TestSummaryAsset {

	@Test
	public void testDollar() {
		SummaryDollar dollar = new SummaryDollar();
		List<AbstractPrice> datas =new ArrayList<>();
		datas.add(new DollarPrice1(1, "1", 1000, 1000, 1, "10102011"));
		dollar.setDatas(datas );
	}

}
