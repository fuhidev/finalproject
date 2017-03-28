package main.tdt.it.finalproject.chart;

import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.jsondata.GoldPrice;
import main.tdt.it.finalproject.jsondata.service.ReadJson;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class TyGiaChart extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1257782022271171807L;
	private static final String GOLD = "Vàng";
	private static final String DOLLAR = "Dola";

	public TyGiaChart(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Years", "Number of Schools", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane(chartPanel);
	}
	private void ReadData(String filename){
		List<GoldPrice> datas = new ArrayList<GoldPrice>();
		ReadJson json = new ReadJson("gold_20170310-20170318");
		json.convertGoldObject(datas);
	}
	private DefaultCategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
//		dataset.addValue(15000, GOLD, "1970");
//		dataset.addValue(30000, GOLD, "1980");
//		dataset.addValue(60000, GOLD, "1990");
//		dataset.addValue(120000, GOLD, "2000");
//		dataset.addValue(240000, GOLD, "2010");
//		dataset.addValue(300000, GOLD, "2014");
//		dataset.addValue(1500, DOLLAR, "1970");
//		dataset.addValue(3000, DOLLAR, "1980");
//		dataset.addValue(6000, DOLLAR, "1990");
//		dataset.addValue(12000, DOLLAR, "2000");
//		dataset.addValue(24000, DOLLAR, "2010");
//		dataset.addValue(30000, DOLLAR, "2014");
		return dataset;
	}
}