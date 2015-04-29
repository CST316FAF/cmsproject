package crm_faf;


import Data.DataCreator;
import Data.DbConnection;
import Data.YearData;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import DataCharts.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ArrayList<YearData> locationTestData;
    private Object lineCanvas;
    private DbConnection db;
    public ChartTest() {
    }

    /**
     *
     */
    @Before
    @Override
    public void setUp() {
    lineGraph = new Chart(db);
    barGraph = new Chart(db);
    pieGraph = new Chart(db);
        try {
            db.connect();
            locationTestData = new DataCreator(db).generateYearData();
        } catch (Exception ex) {
            Logger.getLogger(ChartTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    lineGraph.getCanvas(locationTestData, "line");
    barGraph.getCanvas(locationTestData, "bar");
    pieGraph.getCanvas(locationTestData, "pie");
    lineCanvas = lineGraph.getCanvas();
    }
    
    @After
    public void tearDown() {
    }

    public void testSettings() {
        assert(lineGraph.getCanvas(locationTestData, "line") != null);
    //    assert((new Chart().getCanvas(locationTestData, "wrong").hashCode()) == barGraph.getCanvas(locationTestData, "bar").hashCode());
        assert(barGraph.getCanvas(locationTestData, "bar") != null);
        assert(pieGraph.getCanvas(locationTestData, "pie") != null);        
    }
}
