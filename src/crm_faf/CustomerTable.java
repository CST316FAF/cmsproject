
package crm_faf;

import Data.DbConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class CustomerTable {

    private  TableView table;
    private  List<CustomerEntry> entryData = new ArrayList<CustomerEntry>();
    private  ObservableList<CustomerEntry> entries = FXCollections.observableList(entryData); 
    private  List<String> problemData = new ArrayList<String>();
    private  DbConnection db;
    CustomerTable(DbConnection db) {
        this.db = db;
        Setup();
    }
    
    public void  Setup() {
        this.table = new TableView();
        table.setEditable(false);
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setPrefWidth(100);
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        TableColumn addressColumn = new TableColumn("Street Address");
        addressColumn.setPrefWidth(100);
        addressColumn.setCellValueFactory(
                new PropertyValueFactory<>("streetAddress"));
        TableColumn cityColumn = new TableColumn("City");
        cityColumn.setPrefWidth(200);
        cityColumn.setCellValueFactory(
                new PropertyValueFactory<>("city"));
	TableColumn zipColumn = new TableColumn("Zip");
	zipColumn.setPrefWidth(150);
        zipColumn.setCellValueFactory(
                new PropertyValueFactory<>("zip"));
        TableColumn stateColumn = new TableColumn("State");
        stateColumn.setPrefWidth(100);
        stateColumn.setCellValueFactory(
                new PropertyValueFactory<>("state"));
        TableColumn phoneColumn = new TableColumn("Phone");
        phoneColumn.setPrefWidth(100);
        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone"));
        TableColumn emailColumn = new TableColumn("Email");
        emailColumn.setPrefWidth(200);
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        table.getColumns().addAll(nameColumn, addressColumn, cityColumn,
                zipColumn, stateColumn, phoneColumn, emailColumn ); 
    }

    public TableView getTable() {
        return table;
    }
    
    public void update() throws Exception {
            
            try {
                ResultSet customerfNameResults = db.selectDataColumn("customer", "cfName");
                ResultSet customerlNameResults = db.selectDataColumn("customer", "clName");
                ResultSet streetAddressResults = db.selectDataColumn("customer", "cstreetAddy");
                ResultSet cityResults = db.selectDataColumn("customer", "cCity");
                ResultSet zipResults = db.selectDataColumn("customer", "cZip");
                ResultSet stateResults = db.selectDataColumn("customer", "cState");
                ResultSet phoneResults = db.selectDataColumn("customer", "cPhone");
                ResultSet emailResults = db.selectDataColumn("customer", "cEmail");
                
                List<CustomerEntry> entryUpdate = new ArrayList<CustomerEntry>();
                    while(customerfNameResults.next() && customerlNameResults.next() && streetAddressResults.next()
                                && cityResults.next() && zipResults.next() && stateResults.next() && phoneResults.next() && emailResults.next()) {
                        
                        String name = customerfNameResults.getString(1) + " " +
                                customerlNameResults.getString(1);

                        CustomerEntry entry = new CustomerEntry(name, streetAddressResults.getString(1), cityResults.getString(1),
                                zipResults.getString(1), stateResults.getString(1), phoneResults.getString(1), emailResults.getString(1));
                        
                        entryUpdate.add(entry);
                    }
                entries.removeAll(entries);
                entries.addAll(entryUpdate);
                table.setItems(entries);
                table.setMinHeight(200.5);
                table.autosize();
                }catch(Exception e){System.out.println("1fail");};
    }
}