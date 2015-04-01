/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import javafx.application.Application;
import Data.DbConnection;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Austin
 */
public class LoginScreenTest {
    
    
    public LoginScreenTest() {
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

    
    @Test
    public void testLogin() throws Exception {
        new JFXPanel();
        
        LoginScreen instance = new LoginScreen();
       
        instance.setUp();
        DbConnection connection = new DbConnection();
        connection.connect();
        TextField box = new TextField();
        box.setText("test");
        PasswordField pbox = new PasswordField();
        pbox.setText("testtest");
        instance.setPwBox(new PasswordField());
        instance.setUserTextField(box);
        instance.setPwBox(pbox);
        instance.getuserTextField().setText("test");
        assert(instance.Buttons());
        
        
    }
    
}
