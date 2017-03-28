package test.tdt.it.finalproject.chart;

import org.jfree.ui.RefineryUtilities;

import main.tdt.it.finalproject.chart.TyGiaChart;
import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.jsondata.factory.IReadJsonFactory;
import main.tdt.it.finalproject.jsondata.factory.ReaJsonFactory;
import main.tdt.it.finalproject.jsondata.service.IReadJson;

public class TyGiaChartTest {
	public static void main(String[] args) {
		IReadJsonFactory factory = new ReaJsonFactory();
		IReadJson readDollar = factory.readDollar();
		readDollar.setPath("dollar_20160101-20170201");
		IReadJson readGold = factory.readGold();
		readGold.setPath("gold_20160101-20170201");
		TyGiaChart chart = new TyGiaChart("Tỷ giá");
		chart.setGolds(readGold.getData());
		chart.setDollars(readDollar.getData());
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		try {
			chart.showChart("Biểu đồ giá vàng và giá dollar qua các năm.");
		} catch (NotFoundAssetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chart.setVisible(true);
	}
}
