package main.tdt.it.finalproject.chart;

import java.awt.RenderingHints;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.ui.ApplicationFrame;

/**
 * A demo of the fast scatter plot.
 *
 */
public class FastScatterPlotFrame extends ApplicationFrame {

	private static final long serialVersionUID = 5632806444476901887L;
    public FastScatterPlotFrame(final String title,float[][] data) {

        super(title);
      final NumberAxis domainAxis = new NumberAxis("Rủi ro");
        domainAxis.setAutoRangeIncludesZero(false);
        final NumberAxis rangeAxis = new NumberAxis("Suất sinh lời kỳ vọng");
        rangeAxis.setAutoRangeIncludesZero(false);
        FastScatterPlot plot = new FastScatterPlot(data,domainAxis,rangeAxis);
        final JFreeChart chart = new JFreeChart(title, plot);
//        chart.setLegend(null);

        // force aliasing of the rendered content..
        chart.getRenderingHints().put
            (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final ChartPanel panel = new ChartPanel(chart, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
  //      panel.setHorizontalZoom(true);
    //    panel.setVerticalZoom(true);
        panel.setMinimumDrawHeight(10);
        panel.setMaximumDrawHeight(2000);
        panel.setMinimumDrawWidth(20);
        panel.setMaximumDrawWidth(2000);
        
        setContentPane(panel);

    }
    
}