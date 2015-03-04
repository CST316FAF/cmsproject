package crm_faf;


import Data.DataCreator;
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
    private Chart nullGraph;
    private Chart emptyGraph;
    private ArrayList<Location> locationTestData;
    private Object lineCanvas;

    public ChartTest() {
    }

    /**
     *
     */
    @Before
    @Override
    public void setUp() {
;
    }
    
    @After
    public void tearDown() {
    }

    /*
    Tests if Canvas is always produced from Chart
    */
    public void testChart() {
            Canvas s = new Canvas();
            Class compare = s.getClass();
            lineGraph = new Chart();
            barGraph = new Chart();
            pieGraph = new Chart();
            nullGraph = new Chart();
            emptyGraph = new Chart();
            locationTestData = new DataCreator().createLocations();
            
            assert(lineGraph.getCanvas(locationTestData, "line") != null);
            assert(barGraph.getCanvas(locationTestData, "bar") != null);
            assert(pieGraph.getCanvas(locationTestData, "pie") != null);   
            assert(emptyGraph.getCanvas(new ArrayList<Location>(), "bar") != null);
            assert(lineGraph.getCanvas(locationTestData, "line").getClass().getSuperclass() == compare);
            assert(barGraph.getCanvas(locationTestData, "bar").getClass().getSuperclass() == compare);
            assert(pieGraph.getCanvas(locationTestData, "pie").getClass().getSuperclass()== compare);   
            assert(emptyGraph.getCanvas(new ArrayList<Location>(), "bar").getClass().getSuperclass() == compare);
    }
}
