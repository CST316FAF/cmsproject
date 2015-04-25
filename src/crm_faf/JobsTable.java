
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
    
    JobsTable() {
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
            DbConnection db = new DbConnection();
            
            try {
                db.connect();
                ResultSet customerIdResults = db.selectDataColumn("jobs", "CustomerID", "1");
                ResultSet titleResults = db.selectDataColumn("jobs", "title", "1");
                ResultSet costResults = db.selectDataColumn("jobs", "cost", "1");
                ResultSet dateResults = db.selectDataColumn("jobs", "date", "1");
                ResultSet completedResults = db.selectDataColumn("jobs", "completed", "1");
                
                List<JobsEntry> entryUpdate = new ArrayList<JobsEntry>();
                    while(customerIdResults.next() && titleResults.next() && costResults.next()
                                && dateResults.next() && completedResults.next()) {
                        ResultSet customerName = db.selectDataColumn("customer", "clName", "1");
                        customerName.next();
                        String name = customerName.getString(1);

                        System.out.println(customerIdResults.getString(1));
                        System.out.println(titleResults.getString(1));
                        System.out.println(costResults.getInt(1));
                        System.out.println(dateResults.getDate(1) + customerIdResults.getString(1) + titleResults.getString(1) +  costResults.getInt(1));
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
                System.out.println("number of entries: " + entries.size());
                table.setMinHeight(200.5);
                table.autosize();
                }catch(Exception e){System.out.println("1fail");};
    }
}

