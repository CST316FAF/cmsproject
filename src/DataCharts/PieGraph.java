/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCharts;

import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

public class PieGraph {
    
    public static JFreeChart createChart(PieDataset dataset) {
        
        JFreeChart chart = ChartFactory.createPieChart("Pie Chart Demo 1", 
                dataset, true, true,false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("Data Unavailable");
        plot.setCircular(false);
        plot.setLabelGap(0.01);
        plot.setBackgroundPaint(null);
        chart.setBorderPaint(null);
        chart.setBackgroundPaint(null);
        return chart;
        
    }    
}
