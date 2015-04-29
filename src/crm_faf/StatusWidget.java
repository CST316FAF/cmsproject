/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import Data.DbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class StatusWidget {

    private  TableView table;
    private  List<WidgetEntry> entryData = new ArrayList<>();
    private  ObservableList<WidgetEntry> entries = FXCollections.observableList(entryData); 
    private  List<String> problemData = new ArrayList<String>();
    private DbConnection db;
    
    StatusWidget(DbConnection db) {
        this.db = db;
        Setup();
    }
    
    public void  Setup() {
 //       this.getChildren().add(this);
        this.table = new TableView();
        table.setEditable(false);
        TableColumn sourceColumn = new TableColumn("Source");
        sourceColumn.setPrefWidth(100);
        sourceColumn.setCellValueFactory(
                new PropertyValueFactory<>("source"));
        TableColumn actionColumn = new TableColumn("Action");
        actionColumn.setPrefWidth(100);
        actionColumn.setCellValueFactory(
                new PropertyValueFactory<>("action"));
        TableColumn statusColumn = new TableColumn("Status");
        statusColumn.setPrefWidth(200);
        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status"));
	TableColumn notesColumn = new TableColumn("Notes");
	notesColumn.setPrefWidth(150);
        notesColumn.setCellValueFactory(
                new PropertyValueFactory<>("notes"));
	TableColumn importanceColumn = new TableColumn("Needs Attention");
	importanceColumn.setPrefWidth(50);
        importanceColumn.setCellValueFactory(
                new PropertyValueFactory<>("NAN"));
        table.getColumns().addAll(sourceColumn,  statusColumn, 
                notesColumn, importanceColumn);
 
    }
    public void update(WidgetEntry wEntry) {
        entries.add(wEntry);
       
    }
    public void updateAll(ArrayList<WidgetEntry> wEntries) {
        entries.addAll(wEntries);
    }
    public  TableView getTable() {
        return table;
    }


    public boolean checkStatus() {
        try {
            ResultSet jobsCheck = db.selectDataColumn("jobs", "*");
            List<WidgetEntry> entryUpdate = new ArrayList<>();
            while(jobsCheck.next()) {
                if(jobsCheck.getBoolean("problem")){
                    entryUpdate.add(new WidgetEntry("none", jobsCheck.getString("notes"), "problem", 
            "average", "Jobs"));
                }
            }                
                entries.removeAll(entries);
                entries.addAll(entryUpdate);
                table.setItems(entries);
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(StatusWidget.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
