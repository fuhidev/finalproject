package main.tdt.it.finalproject.chart;

import java.util.Collection;
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
import main.tdt.it.finalproject.util.AssetPriceUtil;

public class TyGiaChart extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1257782022271171807L;
	private static final String GOLD = "Vàng";
	private static final String DOLLAR = "Đô la";
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
		
		Collection<AssetPrice> golds2 = AssetPriceUtil.getGolds(this.golds,true);
		//add value of golds on datasebe
		golds2.forEach(f->{
			dataset.addValue((((GoldPrice) f).getSellPrice()), GOLD+" sell", ((GoldPrice) f).getDateTime());
			dataset.addValue((((GoldPrice) f).getBuyPrice()), GOLD+" buy ", ((GoldPrice) f).getDateTime());
		});
		
		//add value of dollar on dataset
		Collection<AssetPrice> dollars2 = AssetPriceUtil.getDollars(dollars,true);
		dollars2.forEach(f->{
			dataset.addValue(((DollarPrice)f).getSellPrice(), DOLLAR+" sell", ((DollarPrice)f).getDate());
			dataset.addValue(((DollarPrice)f).getBuyCash(), DOLLAR+" BuyCash", ((DollarPrice)f).getDate());
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