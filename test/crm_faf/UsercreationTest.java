/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

//import crm_faf.JavaFXThreadingRule;
import Data.DbConnection;
import crm_faf.CreateUser;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author Austin
 */

public class UsercreationTest {
    //CreateUser userCreator;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    //userCreator = new CreateUser();
    //userCreator.start(null);
    }
    
    @After
    public void tearDown() {
    }
    
    //@Rule 
    //public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

    @Test
    public void testSomeMethod() {
        System.out.println("start");
        Stage primaryStage = null;
        CreateUser instance = new CreateUser();
        Scene start = instance.start(primaryStage);
        
    }
    @Test
    public void testCreation(){
        //CreateUser instance = new CreateUser();
        //final String newfield = "bob";
        //TextField testingField = userCreator.getNewUserTextField();
    }
    
    @Test
    public void testField() throws Exception{
        //CreateUser instance = new CreateUser();
//        userCreator.getNewUserTextField().setText("Name");
//        userCreator.getpwBox0().setText("fire");
//        userCreator.getpwBox1().setText("ice");
//        userCreator.getcreateAcct().fire();
        
        new JFXPanel();
        
        CreateUser instance = new CreateUser();
        Stage Stage = null;
       
        instance.start(Stage);
        Data.DbConnection connection = new Data.DbConnection();
        connection.connect();
        TextField ubox = new TextField();
        ubox.setText("newuser");
        PasswordField pbox = new PasswordField();
        pbox.setText("testtest");
        PasswordField pbox1 = new PasswordField();
        pbox1.setText("testtest");
        instance.setpwBox0(pbox);
        instance.setpwBox1(pbox1);
        instance.setNewUserTextField(ubox);
        
        //instance.getNewUserTextField().setText("test");
        //assert(instance.Buttons());
        
    }
    
}
