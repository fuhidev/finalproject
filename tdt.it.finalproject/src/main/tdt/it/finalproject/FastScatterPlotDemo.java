 package main.tdt.it.finalproject;

import java.awt.RenderingHints;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import main.tdt.it.finalproject.portfolio.modal.Point;

/**
 * A demo of the fast scatter plot.
 *
 */
public class FastScatterPlotDemo extends ApplicationFrame {

    /** A constant for the number of items in the sample dataset. */
    private static final int COUNT = 500000;
    /**
     * Creates a new fast scatter plot demo.
     *
     * @param title  the frame title.
     */
    public FastScatterPlotDemo(final String title) {

        super(title);
//      

    }
    
    public void open(float[][] data ) {
          final NumberAxis domainAxis = new NumberAxis("X");
          domainAxis.setAutoRangeIncludesZero(false);
          final NumberAxis rangeAxis = new NumberAxis("Y");
          rangeAxis.setAutoRangeIncludesZero(false);
          FastScatterPlot plot = new FastScatterPlot(data,domainAxis,rangeAxis);
          final JFreeChart chart = new JFreeChart("Fast Scatter Plot", plot);
//          chart.setLegend(null);

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

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Populates the data array with random values.
     * @return 
     */
    private float[][] populateData() {
    	float[][] data = new float[2][COUNT];
        for (int i = 0; i < data[0].length; i++) {
            final float x = (float) i + 100000;
            data[0][i] = x;
            data[1][i] = 100000 + (float) Math.random() * COUNT;
        }
        return data;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final FastScatterPlotDemo demo = new FastScatterPlotDemo("Fast Scatter Plot Demo");
        float minY = 0.02937913f;
		float maxY=0.05355954f;
		float[][] data = new float[2][COUNT];
		float alpha = 32.13677f;
		float beta=0.9931187f;
		float gamma = 0.031796522f;
		for (float i = 0, y = minY; y < maxY; y += 0.000001f,i++) {
			
			float x = (float) Math.sqrt(alpha * y * y - 2 * beta * y + gamma);
			data[0][(int) i] = x;
			data[1][(int) i] = y;
		}
        demo.open(data);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}