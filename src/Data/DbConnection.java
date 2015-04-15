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
    
    public boolean connect() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
          
            //connection = DriverManager.getConnection("jdbc:mysql:sql3.freemysqlhosting.net:3306",
            //      "sql368756", "qG6%pU4%");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmsdb",
                  "root", "");
            System.out.println("Connected!");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/phpmyadmin/cmsdb",
              //    "root", "");
        } 
        catch (ClassNotFoundException e) {
              System.out.println("no connection driver found");
              System.out.println(e);
             // Could not find the database driver
              return false;
        }
        catch (SQLException ex) {
            
            System.out.println("connection failed");
            connection.close();
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    public ResultSet selectDataColumn(String table, String column, String identifier ) {
        ResultSet results = null;
        try {
            System.out.println("SELECT " + column 
                    + " FROM " + table + " WHERE techID = \"" + identifier + "\"");
            PreparedStatement statement = connection.prepareStatement("SELECT " + column 
                    + " FROM " + table + " WHERE techID = \"" + identifier + "\"");
            //String query = "SELECT \"" + column 
            //        + "\" FROM \"" + table + "\" WHERE P_ID = \"" + identifier + "\"";
            System.out.println(statement + column);
            results = statement.executeQuery();
            //if(!results.isBeforeFirst()) {
             //   System.out.println("no Data");
           // }
            while (results.next()){
                String data = results.getString(1);
                //String username = results.getString("USERNAME");
 
                System.out.println(column + ": " + data);
		//System.out.println("username : " + username);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }
    public ResultSet selectDataColumns(String table, String column, ArrayList<String> identifiers, ArrayList<String> identifierColumn) {
        ResultSet results = null;
        try {
            Statement statement = connection.createStatement();
             String query = new String("SELECT + * FROM" + column  + " "
                    + " + FROM "+ table + " WHERE ");
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
            String query = new String("INSERT INTO " + table + "Values (");
            for(int x = 0; x < values.size(); x++) {
                query += values.get(x) + ",";
            }
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
            System.out.println("username: " + userName);
            System.out.println("password: " + password);
            
            ResultSet results = statement.executeQuery("SELECT 1 FROM user WHERE userName = \"" 
                    + userName + "\" and passWd = \"" + password + "\""); 
            if(results.first())
                answer = true;
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return answer;
    }
     
}
