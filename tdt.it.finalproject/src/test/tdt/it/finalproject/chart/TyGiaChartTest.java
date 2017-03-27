package test.tdt.it.finalproject.chart;

import org.jfree.ui.RefineryUtilities;

import main.tdt.it.finalproject.chart.TyGiaChart;

public class TyGiaChartTest {
	public static void main( String[ ] args ) 
	   {
	      TyGiaChart chart = new TyGiaChart("Tỷ giá", "Biểu đồ giá vàng và dollar qua các năm?");
	      chart.pack( );          
	      RefineryUtilities.centerFrameOnScreen( chart );          
	      chart.setVisible( true ); 
	   }
}
