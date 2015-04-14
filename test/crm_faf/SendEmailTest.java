/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kunaalgodiwala
 */
public class SendEmailTest {
    
    public SendEmailTest() {
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
     * Test of addHBox method, of class SendEmail.
     */
    @Test
    public void testAddHBox() {
        System.out.println("addHBox");
        SendEmail instance = new SendEmail();
        HBox expResult = null;
        HBox result = instance.addHBox();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addcenterVBox method, of class SendEmail.
     */
    @Test
    public void testAddcenterVBox() {
        System.out.println("addcenterVBox");
        SendEmail instance = new SendEmail();
        VBox expResult = null;
        VBox result = instance.addcenterVBox();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class SendEmail.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Stage primaryStage = null;
        SendEmail instance = new SendEmail();
        instance.start(primaryStage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
