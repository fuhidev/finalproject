package main.tdt.it.finalproject.chart;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import main.tdt.it.finalproject.exception.NotFoundAssetException;
import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.jsondata.GoldPrice;

public class TyGiaChart extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1257782022271171807L;
	private static final String GOLD = "Vàng";
	private static final String DOLLAR = "Dola";
	private static final String YEAR = "Năm";
	private static final String MONEY = "Tiền";
	
	private List<AssetPrice> golds;
	private List<AssetPrice> dollars;

	public TyGiaChart(String applicationTitle) {
		super(applicationTitle);
	}
	public void showChart(String chartTitle) throws NotFoundAssetException{
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, YEAR, MONEY, createDataset(),
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane(chartPanel);
	}
	private DefaultCategoryDataset createDataset() throws NotFoundAssetException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		if(this.golds == null || this.golds.size() == 0){
			throw new NotFoundAssetException(GOLD+" please call setGolds()");
		}
		if(this.dollars== null || this.dollars.size() == 0){
			throw new NotFoundAssetException(DOLLAR+" please call setDollar");
		}
		
		//add value of golds on dataset
		this.golds.forEach(f->{
			dataset.addValue((((GoldPrice) f).getSellPrice()), GOLD, ((GoldPrice) f).getDateTime());
		});
		
		//add value of dollar on dataset
		this.dollars.forEach(f->{
			dataset.addValue(((DollarPrice)f).getSellPrice(), DOLLAR, ((DollarPrice)f).getDate());
		});
		
		return dataset;
	}
	public List<AssetPrice> getGolds() {
		return golds;
	}
	public void setGolds(List<AssetPrice> golds) {
		this.golds = golds;
	}
	public List<AssetPrice> getDollars() {
		return dollars;
	}
	public void setDollars(List<AssetPrice> dollars) {
		this.dollars = dollars;
	}
	
}