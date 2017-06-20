package test.tdt.it.finalproject.jsondata.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.tdt.it.finalproject.jsondata.DollarPrice1;
import main.tdt.it.finalproject.jsondata.service.WriterJson;
import main.tdt.it.finalproject.modal.AbstractPrice;

public class TestWriterJson {
	private WriterJson writer = new WriterJson();

	@Test
	public void test() {
		writer.setFileName("dollar");
		List<AbstractPrice> datas = new ArrayList<>();
		datas.add(new DollarPrice1(0, "USD", "22,745", "22,745", "22,815", "20170317"));
		writer.export(datas);
	}

}
