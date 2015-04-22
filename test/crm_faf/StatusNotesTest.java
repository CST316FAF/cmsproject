/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import crm_faf.StatusNotes;
import javafx.application.Application;
import javafx.scene.layout.HBox;

/**
 *
 * @author ramtin
 */
public class StatusNotesTest extends StatusNotes{
    
    public StatusNotesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Application.launch();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        test();
        
    }
    
    @After
    public void tearDown() {
       
        
    }

    @Test
    public void test() {
        StatusNotes n = new StatusNotes();
        n.addHBox();
        HBox hboxTest = new HBox();
        assertEquals(n.addHBox(), hboxTest);
        System.out.println("Test Successful!");
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    
}
