/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class StatusWidget extends Updateable {

    private  TableView table;
    private  List<WidgetEntry> entryData = new ArrayList<WidgetEntry>();
    private  ObservableList<WidgetEntry> entries = FXCollections.observableList(entryData); 
    private  boolean hasFeed = false;

    StatusWidget() {
        Setup();
    }
    
    public void  Setup() {
 //       this.getChildren().add(this);
        this.table = new TableView();
        table.setEditable(false);
        TableColumn sourceColumn = new TableColumn("Source");
        sourceColumn.setPrefWidth(100);
        sourceColumn.setCellValueFactory(
                new PropertyValueFactory<WidgetEntry,String>("source"));
        TableColumn actionColumn = new TableColumn("Action");
        actionColumn.setPrefWidth(100);
        actionColumn.setCellValueFactory(
                new PropertyValueFactory<WidgetEntry,String>("action"));
        TableColumn statusColumn = new TableColumn("Status");
        statusColumn.setPrefWidth(200);
        statusColumn.setCellValueFactory(
                new PropertyValueFactory<WidgetEntry,String>("status"));
	TableColumn notesColumn = new TableColumn("Notes");
	notesColumn.setPrefWidth(150);
        notesColumn.setCellValueFactory(
                new PropertyValueFactory<WidgetEntry,String>("notes"));
	TableColumn importanceColumn = new TableColumn("Needs Attention");
	importanceColumn.setPrefWidth(50);
        importanceColumn.setCellValueFactory(
                new PropertyValueFactory<WidgetEntry,String>("NAN"));
        table.getColumns().addAll(sourceColumn, actionColumn, statusColumn, 
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

    @Override
    public boolean getIsFeed() {
        return this.hasFeed;
    }
    
    public void setFeed(String url) {
        RSSFeedInput input = new RSSFeedInput(url);
        entries.removeAll(entries);
        entries.addAll(input.getList());
        table.setItems(entries);
        hasFeed = true;
        table.columnResizePolicyProperty();
    }
    
}
