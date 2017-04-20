package test.tdt.it.finalproject.chart;

import java.util.ArrayList;
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

		// chay tam
		// tao mot mang chua tat ca ten file de lay du lieu
		String[] dollarsDay = { "dollar_20150101-20150201", "dollar_20150101-20160101", "dollar_20160101-20160201",
				"dollar_20160101-20170101", "dollar_20170101-20170201" };

		// luu tru danh sach dollar khi doc du lieu
		List<AssetPrice> dollars = new ArrayList<>();
		// duyet tat ca cac file co trong mang
		for (String dld : dollarsDay) {
			// set duong dan file
			readDollar.setPath(dld);
			// them tat ca cac du lieu doc duoc tu phuong thuc getData, sau do
			// luu tru vao danh sach dollars
			dollars.addAll(readDollar.getData());
		}

		IReadJson readGold = factory.readGold();
		// tao mot mang chua tat ca ten file de lay du lieu
		String[] goldsDay = { "gold_20160101-20160201",
				"gold_20160101-20170101",
				"gold_20170101-20170201" };

		// luu tru danh sach dollar khi doc du lieu
		List<AssetPrice> golds = new ArrayList<>();
		// duyet tat ca cac file co trong mang
		for (String gd : goldsDay) {
			// set duong dan file
			readGold.setPath(gd);
			// them tat ca cac du lieu doc duoc tu phuong thuc getData, sau do
			// luu tru vao danh sach dollars
			golds.addAll(readGold.getData());
		}
		TyGiaChart chart = null;
		try {
			chart = new TyGiaChart("Tỷ giá", "Biểu đồ giá vàng và giá dollar qua các năm.", golds, dollars);
		} catch (NotFoundAssetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
