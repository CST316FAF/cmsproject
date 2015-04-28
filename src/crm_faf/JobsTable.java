
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


public class JobsTable {

    private  TableView table;
    private  List<JobsEntry> entryData = new ArrayList<JobsEntry>();
    private  ObservableList<JobsEntry> entries = FXCollections.observableList(entryData); 
    private  List<String> problemData = new ArrayList<String>();
    private final DbConnection db;
    
    JobsTable(DbConnection db) {
        this.db = db;
        Setup();
    }
    
    public void  Setup() {
        this.table = new TableView();
        table.setEditable(false);
        TableColumn titleColumn = new TableColumn("Title");
        titleColumn.setPrefWidth(100);
        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        TableColumn customerColumn = new TableColumn("Customer");
        customerColumn.setPrefWidth(100);
        customerColumn.setCellValueFactory(
                new PropertyValueFactory<>("customer"));
        TableColumn costColumn = new TableColumn("Cost");
        costColumn.setPrefWidth(200);
        costColumn.setCellValueFactory(
                new PropertyValueFactory<>("cost"));
	TableColumn datesColumn = new TableColumn("Date");
	datesColumn.setPrefWidth(150);
        datesColumn.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        table.getColumns().addAll(titleColumn, customerColumn, costColumn, 
                datesColumn); 
    }

    public TableView getTable() {
        return table;
    }
    
    public void update() throws Exception {
            
            try {
                ResultSet customerIdResults = db.selectDataColumn("jobs", "CustomerID");
                ResultSet titleResults = db.selectDataColumn("jobs", "title");
                ResultSet costResults = db.selectDataColumn("jobs", "cost");
                ResultSet dateResults = db.selectDataColumn("jobs", "date");
                ResultSet completedResults = db.selectDataColumn("jobs", "completed");
                
                List<JobsEntry> entryUpdate = new ArrayList<JobsEntry>();
                    while(customerIdResults.next() && titleResults.next() && costResults.next()
                                && dateResults.next() && completedResults.next()) {
                        ResultSet customerName = db.selectDataColumn("customer", "clName");
                        customerName.next();
                        String name = customerName.getString(1);
                        String completed = "no";
                        if(completedResults.getInt(1) == 1) {
                            completed = "yes";
                        }
                        JobsEntry entry = new JobsEntry(name, titleResults.getString(1), costResults.getInt(1) + "", dateResults.getDate(1).toLocalDate().toString(), completed);
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

