package main.tdt.it.finalproject.chart;


import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * A demo of the fast scatter plot.
 *
 */
public class XYLineChart extends ApplicationFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XYLineChart(final String title,XYDataset data) {

        super(title);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                title,
                "Category" ,
                "Score" ,
                data,
                PlotOrientation.VERTICAL ,
                true , true , false);
        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = xylineChart.getXYPlot( );
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesPaint( 1 , Color.GREEN );
        renderer.setSeriesPaint( 2 , Color.YELLOW );
        plot.setRenderer( renderer ); 
        setContentPane( chartPanel ); 

    }

}