/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCharts;


import Data.MonthData;
import Data.YearData;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import org.jfree.fx.FXGraphics2D;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class Chart {    

    private Canvas canvas;

    public Canvas getCanvas(ArrayList<YearData> locs, String type) {
        try {
            if(locs != null && !locs.isEmpty()) {
                switch (type) {
                    case "line":
                        this.canvas = new ChartCanvas(LineGraph.createChart(createXYDataset(locs)));
                        break;
                    case "pie":
                        this.canvas = new ChartCanvas(PieGraph.createChart(createPieDataset(locs)));
                        break;
                    case "bar":
                        this.canvas = new ChartCanvas(BarGraph.createChart(createCategoryDataset(locs)));
                        break;
                    default:
                        this.canvas = new ChartCanvas(BarGraph.createChart(createCategoryDataset(locs)));
                        break;
                }
            }
            else
            {
                this.canvas = new FailedChart();
            }
        } catch(Exception ex) {
            this.canvas = new FailedChart();
            Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
        }    
            canvas.setHeight(400);
            canvas.setWidth(600);
            canvas.getWidth();
            return canvas;
        
    }
    public Canvas getCanvas() {
        return canvas;
    }

    static class ChartCanvas extends Canvas { 
 
        JFreeChart chart;    
        
        private FXGraphics2D g2;
        
        public ChartCanvas(JFreeChart chart) {
            this.chart = chart;
            this.g2 = new FXGraphics2D(getGraphicsContext2D());
            // Redraw canvas when size changes. 
            widthProperty().addListener(evt -> draw()); 
            heightProperty().addListener(evt -> draw()); 
        }  
        
        private void draw() { 
            double width = getWidth(); 
            double height = getHeight();
            getGraphicsContext2D().clearRect(0, 0, width, height);
            this.chart.draw(this.g2, new Rectangle2D.Double(0, 0, width, 
                    height));
        } 
        
        @Override 
        public boolean isResizable() { 
            return true;
        }  
        
        @Override 
        public double prefWidth(double height) { return getWidth(); }  
        
        @Override 
        public double prefHeight(double width) { return getHeight(); } 
    }

    /**
     * Creates a dataset, consisting customers per area code
     * Methods are for display purposes at this point and may be refactored and 
     * modified based on necessity
     */
    private static XYDataset createXYDataset(ArrayList<YearData> locs) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        for(int x = 0; x < locs.size(); x++) {
            for(int y = 0; y < 12; y++) {
                MonthData[] months = locs.get(x).getMonths();
                for(int z = 0; z < months[y].getAreaCodeData().size(); z++){
                    TimeSeries time = new TimeSeries("" + months[y].getAreaCodeData().get(x).getAreaCode());
                    months = locs.get(x).getMonths();
                    for(int f =0; f < months.length; f++) {
                        if(months[f].getAreaCodeData().size() > f) {
                            time.add(months[f].getMonth(), months[f].getAreaCodeData().get(f).getAreaCode());
                        }
                    }
                    dataset.addSeries(time);
                }
            } 
        }
        return dataset;
    }

    private static PieDataset createPieDataset(ArrayList<YearData> locs) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(int x = 0; x < locs.size(); x++) {
            for(int y = 0; y < 12; y++) {
                MonthData[] months = locs.get(x).getMonths();
                for(int z = 0; z < 12; z++){
                    if(months[y].getAreaCodeData().size() > 0){
                        dataset.setValue("" +months[y].getMonth().toString(), 
                                months[y].getAreaCodeData().get(z).getAreaCode());
                    }
                }
            }
        }    
        for(int x = 0; x < locs.size(); x++) {         
            ;
        }
        return dataset;
    }
    
    private CategoryDataset createCategoryDataset(ArrayList<YearData> locs) {
       
        final String series2 = "Technicians";
        final String series1 = "Customers";

        ArrayList<String> category = new ArrayList<String>();
        for(int x = 0; x < locs.size(); x++) { 
                MonthData[] months = locs.get(x).getMonths();
                for(int y = 0; y < 12; y++) {
                    for(int z = 0; z < 12; z++) {
                        if( months[y] != null && months[y].getAreaCodeData().size() > z && 
                                months[y].getAreaCodeData().get(z).getAreaCode() != 0) {
                         category.add("" + months[y].getAreaCodeData().get(z).getAreaCode());
                        }
                    }
                   
                }
        }
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(int x = 0; x < category.size(); x++) {
            if(locs.size() > x) {
                MonthData[] months = locs.get(x).getMonths();
                for(int y = 0; y < 12; y++) {

                    for(int z = 0; z < 12; z++) {
                        if( months[y] != null && months[y].getAreaCodeData().size() > z && months[y].getAreaCodeData() != null && 
                                    months[y].getAreaCodeData().get(z).getAreaCode() != 0) {
                            dataset.addValue(months[y].getAreaCodeData().get(z).getCustomerNumber(), series1, category.get(x));
                            dataset.addValue(months[y].getAreaCodeData().get(z).getJobNumber(), series2, category.get(x));
                            category.add("" + months[y].getAreaCodeData().get(z).getAreaCode());
                        }
                    }
                }
            }
        }  
        return dataset;
        
    }
}
