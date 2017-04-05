package test.tdt.it.finalproject.chart;

import java.util.List;

import org.jfree.ui.RefineryUtilities;

import main.tdt.it.finalproject.chart.TyGiaChart;
import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.factory.IReadJsonFactory;
import main.tdt.it.finalproject.jsondata.factory.ReaJsonFactory;
import main.tdt.it.finalproject.jsondata.service.IReadJson;

public class TyGiaChartTest {
	public static void main(String[] args) {
		IReadJsonFactory factory = new ReaJsonFactory();
		IReadJson readDollar = factory.readDollar();
		readDollar.setPath("dollar_20161231-20170404");
		List<AssetPrice> dollars = readDollar.getData();
		readDollar.setPath("dollar_20150101-20161230");
		dollars.addAll(readDollar.getData());
		
		
		IReadJson readGold = factory.readGold();
		readGold.setPath("gold_20150920-20170404");
		List<AssetPrice> golds = readGold.getData();
		readGold.setPath("gold_20090921-20110920");
		golds.addAll(readGold.getData());
		readGold.setPath("gold_20110921-20130919");
		golds.addAll(readGold.getData());
		readGold.setPath("gold_20130920-20150919");
		golds.addAll(readGold.getData());
		TyGiaChart chart = new TyGiaChart("Tá»· giÃ¡");
		chart.setGolds(golds);
		chart.setDollars(dollars);
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		try {
			chart.showChart("Biểu đồ giá vàng và giá đôla qua các năm.");
		} catch (NotFoundAssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chart.setVisible(true);
	}
}
