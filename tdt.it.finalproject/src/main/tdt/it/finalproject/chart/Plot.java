package main.tdt.it.finalproject.chart;

import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

public class Plot {
	public static void showLine(final String title,XYDataset data) {
		XYLineChart plot = new XYLineChart(title, data);
		plot.pack();
        RefineryUtilities.centerFrameOnScreen(plot);
        plot.setVisible(true);
	}
	public static void showScatter(final String title,float[][] data) {
		FastScatterPlotFrame plot = new FastScatterPlotFrame(title, data);
		plot.pack();
        RefineryUtilities.centerFrameOnScreen(plot);
        plot.setVisible(true);
	}
}
