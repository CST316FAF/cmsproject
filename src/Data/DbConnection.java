/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * Sets up connection and provides for db query
 */
public class DbConnection {
    private Connection connection = null;
    private String identifier;
    
    public boolean connect() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmsdb",
                  "root", "");

        } 
        catch (ClassNotFoundException e) {
              System.out.println("no connection driver found");
              System.out.println(e);
             // Could not find the database driver
              return false;
        }
        catch (SQLException ex) {
            
            connection.close();
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    public ResultSet selectDataColumn(String table, String column) {
        ResultSet results = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT " + column 
                    + " FROM " + table + " WHERE P_ID = \"" + identifier + "\"");
            results = statement.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }
    public ResultSet selectDataColumn(String table, String column, String modifier, String modifierColumn) {
        ResultSet results = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT " + column 
                    + " FROM " + table + " WHERE P_ID = \"" + identifier + "\" And " + modifierColumn + " = \"" + modifier + "\"");

            results = statement.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }
    public ResultSet selectDataColumns(String table, String column, ArrayList<String> identifiers, ArrayList<String> identifierColumn) {
        ResultSet results = null;
        try {
            
            Statement statement = connection.createStatement();
             String query = "SELECT + * FROM" + column  + " "
                     + " + FROM "+ table + " WHERE ";
            for(int x = 0; x < identifiers.size(); x++) {
               if (x > 0)
                   query += " and ";
               query = query + identifierColumn.get(x) + " = \"" + identifiers.get(x) + "\"";
            }
            results = statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }
    
    public void insertData(String table, ArrayList<String> values) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO " + table + "Values (";
            query = values.stream().map((value) -> value + ",").reduce(query, String::concat);
            query += ")"; 
            statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateData(String table, String column, String value) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("Update + " + table  + " Set " + column 
                    + " = \"" + value + "\"");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean login(String userName, String password) {
        boolean answer = false;
        Statement statement;
        try {
            statement = connection.createStatement();
            
            ResultSet results = statement.executeQuery("SELECT 1 FROM user WHERE userName = \"" 
                    + userName + "\" and passWd = \"" + password + "\""); 
            if(results.first()) {
                identifier = results.getInt(1) +"";
                answer = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return answer;
    }

    public String getId() {
        return identifier;
    }
     
}
