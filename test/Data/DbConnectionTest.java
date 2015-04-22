/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * requires local database setup
 */
public class DbConnectionTest {
    DbConnection instance;
    public DbConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        instance = new DbConnection();
        instance.connect();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of connect method, of class DbConnection.
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("connect");
        DbConnection instance2 = new DbConnection();

        assertTrue(instance2.connect());
    }

    /**
     * Test of selectDataColumn method, of class DbConnection.
     */
    @Test
    public void testSelectDataColumn() {
        System.out.println("selectDataColumn");
        String table = "user";
        String column = "username";
        String identifier = "test";
        instance = new DbConnection();
        ResultSet expResult = null;
        ResultSet result = instance.selectDataColumn(table, column, identifier);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of selectDataColumns method, of class DbConnection.
     */
    @Test
    public void testSelectDataColumns() {
        System.out.println("selectDataColumns");
        String table = "user";
        String column = "name";
        ArrayList<String> identifiers = new ArrayList<String>();
        ArrayList<String> identifierColumn = new ArrayList<String>();
        identifiers.add("test");
        identifierColumn.add("username");
        instance = new DbConnection();
        ResultSet expResult = null;
        ResultSet result = instance.selectDataColumns(table, column, identifiers, identifierColumn);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertData method, of class DbConnection.
     */
    @Test
    public void testInsertData() {
        System.out.println("insertData");
        String table = "";
        ArrayList<String> values = null;
        instance = new DbConnection();
        instance.insertData(table, values);
    }

    /**
     * Test of updateData method, of class DbConnection.
     */
    @Test
    public void testUpdateData() {
        System.out.println("updateData");
        String table = "user";
        String column = "username";
        String value = "testUser";
        instance.updateData(table, column, value);
    }

    /**
     * Test of login method, of class DbConnection.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String userName = "test";
        String password = "testtest";
        instance = new DbConnection();
        boolean expResult = true;
        boolean result = instance.login(userName, password);
        assertEquals(expResult, result);
        System.out.println("login");
        userName = "not";
        password = "password";
        expResult = false;
        result = instance.login(userName, password);
        assertEquals(expResult, result);
    }
    
}
