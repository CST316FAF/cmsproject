/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Data.Location;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import DataCharts.*;
import java.util.ArrayList;
import junit.framework.TestCase;
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

/**
 *
 * @author Davis
 */
public class ChartTest extends TestCase {
    private Chart lineGraph;
    private Chart pieGraph;
    private Chart barGraph;
    private ArrayList<Location> locationTestData;

    public ChartTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    Chart lineGraph;
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    Chart lineGraph = new Chart();
    barGraph = new Chart();
    pieGraph = new Chart();
    locationTestData = new ArrayList<Location>();
    lineGraph.getCanvas(locationTestData, "barTest");
    barGraph.getCanvas(locationTestData, "pieTest");
    pieGraph.getCanvas(locationTestData, "lineTest");
    }
    
    @After
    public void tearDown() {
    }

    public void testSettings() {
        assert(lineGraph.getCanvas(locationTestData, "line") != null);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
