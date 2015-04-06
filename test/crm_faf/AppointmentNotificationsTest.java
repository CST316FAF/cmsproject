/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

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
public class AppointmentNotificationsTest {
    
    public AppointmentNotificationsTest() {
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
     * Test of newMessage method, of class AppointmentNotifications.
     */
    @Test
    public void testNewMessage() {
        System.out.println("newMessage");
        AppointmentNotifications instance = new AppointmentNotifications();
        instance.newMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class AppointmentNotifications.
     * @throws java.lang.Exception
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        Stage primaryStage = null;
        AppointmentNotifications instance = new AppointmentNotifications();
        instance.start(primaryStage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private static class AppointmentNotifications {

        public AppointmentNotifications() {
        }

        private void start(Stage primaryStage) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void newMessage() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    /**
     * Test of main method, of class AppointmentNotifications.
     */
    @Test
    public void testMain() {
        System.out.println("emailNotify");
        //String[] args = null;
        crm_faf.AppointmentNotifications.emailNotify();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}