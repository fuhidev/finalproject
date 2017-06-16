package test.tdt.it.finalproject.jsondata.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.jsondata.service.WriterJson;

public class WriterJsonTest {
	private WriterJson writer = new WriterJson();

	@Test
	public void test() {
		writer.setFileName("dollar");
		List<AssetPrice> datas = new ArrayList<>();
		datas.add(new DollarPrice(0, "USD", "22,745", "22,745", "22,815", "20170317"));
		writer.export(datas);
	}

}
