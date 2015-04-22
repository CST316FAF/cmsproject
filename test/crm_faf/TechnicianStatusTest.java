/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import java.util.Hashtable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TechnicianStatusTest {
    
    public TechnicianStatusTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAttributes method, of class TechnicianStatus.
     */
    @Test
    public void testGetAttributes() {
        System.out.println("getAttributes");
        TechnicianStatus instance = new TechnicianStatus();
        StackPane root = new StackPane();
        instance.setRoot(root);
        Hashtable result = instance.getAttributes();
        assertTrue(result != null);
        assertTrue(result.get("root") == root);
    }

    /**
     * Test of setAttributes method, of class TechnicianStatus.
     */
    @Test
    public void testSetAttributes() {
        System.out.println("setAttributes");
        Hashtable attValues = new Hashtable();
        attValues.put("border", new BorderPane());
        attValues.put("root", new StackPane());
        attValues.put("windowTopBox", new VBox());
        TechnicianStatus instance = new TechnicianStatus();
        instance.setAttributes(attValues);
    }
    
}
