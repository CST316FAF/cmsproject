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
        //instance.setPwBox(pbox);
        instance.setUserTextField(box);
        instance.setPwBox(pbox);
        instance.getuserTextField().setText("test");
        assert(instance.Buttons());
        
        TextField box1 = new TextField();
        box1.setText("test");
        PasswordField pbox1 = new PasswordField();
        pbox1.setText("test1");
        instance.setPwBox(pbox1);
        instance.setUserTextField(box1);
        instance.getuserTextField().setText("test");
        assert(instance.Buttons());
        
        TextField box2 = new TextField();
        box2.setText("test");
        PasswordField pbox2 = new PasswordField();
        pbox2.setText("");
        instance.setPwBox(pbox2);
        instance.setUserTextField(box2);
        instance.getuserTextField().setText("test");
        assert(instance.Buttons());
        
        TextField box3 = new TextField();
        box3.setText("");
        PasswordField pbox3 = new PasswordField();
        pbox3.setText("");
        instance.setPwBox(pbox3);
        instance.setUserTextField(box3);
        instance.getuserTextField().setText("test");
        assert(instance.Buttons());
        
        TextField box4 = new TextField();
        box4.setText("");
        PasswordField pbox4 = new PasswordField();
        pbox4.setText("testtest");
        instance.setPwBox(pbox4);
        instance.setUserTextField(box4);
        instance.getuserTextField().setText("test");
        assert(instance.Buttons());
        
        TextField box5 = new TextField();
        box5.setText("");
        PasswordField pbox5 = new PasswordField();
        pbox5.setText("test1");
        instance.setPwBox(pbox5);
        instance.setUserTextField(box5);
        instance.getuserTextField().setText("test");
        assert(instance.Buttons());
    
    }
    
}
