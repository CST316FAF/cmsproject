/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Data.DbConnection;

/**
 *
 * @author ramtin
 */
public class CustomerFormTest {
    
    CustomerForm formTest = new CustomerForm();
    
    
    public CustomerFormTest() {
        
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void submitButton() {
        
        CustomerForm formTest = new CustomerForm();
       
        DbConnection connection = new DbConnection();
        connection.connect();
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField addressFeild = new TextField();
        TextField cityField = new TextField();
        TextField stateField = new TextField();
        TextField zipField = new TextField();
        TextField telephoneField = new TextField();
        TextField emailField = new TextField();
        
        firstNameField.setText("first name test");
        lastNameField.setText("last name test");
        addressFeild.setText("address test");
        cityField.setText("city test");
        stateField.setText("state test");
        zipField.setText("zip test");
        telephoneField.setText("telephone test");
        emailField.setText("email teset");
        
        
        
    }
}
