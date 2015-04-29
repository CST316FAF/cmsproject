/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataCharts;


import Data.DbConnection;
import Data.MonthData;
import Data.YearData;
import java.awt.geom.Rectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import org.jfree.fx.FXGraphics2D;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class Chart {    

    private Canvas canvas;
    private final DbConnection db;
    
    public Chart(DbConnection db){
        this.db = db;
    }
    
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
            
            return new ChartCanvas(BarGraph.createChart(createCategoryDataset(locs)));
        
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
    private  XYDataset createXYDataset(ArrayList<YearData> locs) {

        try {
            ResultSet locations = db.selectDataColumn("customer", "*");
            TimeSeriesCollection dataset = new TimeSeriesCollection();
            
            
            Calendar defCal = Calendar.getInstance();
            defCal.set(2015, 1, 1);
            int defMonthNum = defCal.get(Calendar.MONTH);
            TimeSeries defTime = new TimeSeries("" + defCal.get(Calendar.MONTH));
            Month defMonth = new Month(defMonthNum, 2015);
            defTime.addOrUpdate(defMonth ,0);
            
            defCal = Calendar.getInstance();
            defCal.set(2015, 6, 6);
            defMonthNum = defCal.get(Calendar.MONTH);
            defTime = new TimeSeries("" + defCal.get(Calendar.MONTH));
            defMonth = new Month(defMonthNum, 2015);
            defTime.addOrUpdate(defMonth ,0);
            
            
           
            dataset.addSeries(defTime);
            while(locations.next()){
                
                ResultSet jobDates = db.selectDataColumn("jobs", "date", locations.getString(2), "CustomerID");
                TimeSeries time = new TimeSeries("" + locations.getInt(7));
                int n = 0;
                Month month = null;
                while(jobDates.next()) {
                    if(n == 0) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(jobDates.getDate(1));
                        int monthNum = cal.get(Calendar.MONTH);
                        month = new Month(monthNum, 2015);
                    }
                     n++;
                       
                    }                
                if(month != null) {
                    time.addOrUpdate(month ,n );
                    dataset.addSeries(time);
                }
            }
            return dataset;
        } catch (SQLException ex) {
            Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static PieDataset createPieDataset(ArrayList<YearData> locs) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        for(int x = 0; x < locs.size(); x++) {
            for(int y = 0; y < 12; y++) {
                MonthData[] months = locs.get(x).getMonths();
                for(int z = 0; z < 12; z++){
                    if(months[y].getAreaCodeData().size() > z){
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
        
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        final String series1 = "Customers";
            
        try {
            ResultSet locations = db.selectDataColumn("customer", "*");
            
            
            Calendar defCal = Calendar.getInstance();
            defCal.set(2015, 1, 1);
            int defMonthNum = defCal.get(Calendar.MONTH);
            Month defMonth = new Month(defMonthNum, 2015);
            dataset.addValue(0, series1, defMonth.toString());
            
            defCal = Calendar.getInstance();
            defCal.set(2015, 6, 6);
            defMonthNum = defCal.get(Calendar.MONTH);
            defMonth = new Month(defMonthNum, 2015);
            dataset.addValue(0, series1, defMonth.toString());
            
            
           
         //   while(locations.next()){
                
                ResultSet jobDates = db.selectDataColumn("jobs", "*");
                int n = 0;
                Month month = null;
                while(jobDates.next()) {

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(jobDates.getDate("date"));
                        int monthNum = cal.get(Calendar.MONTH);
                        month = new Month(monthNum, 2015);
                        if(month != null) { 
                            dataset.addValue(1, series1, month.toString());
                        }
                }
                       
                                    

          // }
            return dataset;
        } catch (SQLException ex) {
            Logger.getLogger(Chart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;       
        
    }
 
}

