
package crm_faf;
	
import Data.DataCreator;
import Data.DbConnection;
import Data.YearData;
import DataCharts.Chart;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.cell.PropertyValueFactory;



public class StatusPage extends TransitionScene {
        private WindowTools toolbar;
        private WindowToolbar bar;
        private  TableView table;
        private final  List<StatusEntry> entryData = new ArrayList<>();
        private final  ObservableList<StatusEntry> entries = FXCollections.observableList(entryData);
        private Scene scene;
        private BorderPane pane2;
        private Stage primaryStage;
        private GridPane pane;
        private Tab graphTab;
        private Tab technicianTab;
        private Tab customerTab;
        private Tab jobTab;
        private TabPane statusPane;
        private Chart barGraph;
        private DbConnection db;


        
	public Scene start(Stage primaryStage, WindowTools tBar, DbConnection db) {
                this.db = db;
                this.toolbar = tBar;
		this.primaryStage = primaryStage;
            try {
                setup();
                //AppointmentNotifications notify = new AppointmentNotifications();
                //notify.newMessage();
            } catch (Exception ex) {
                Logger.getLogger(StatusPage.class.getName()).log(Level.SEVERE, null, ex);
            }
	
		primaryStage.setScene(scene);
		primaryStage.show();
            try {
                this.update();
            } catch (Exception ex) {
                Logger.getLogger(StatusPage.class.getName()).log(Level.SEVERE, null, ex);
            }
		return scene;
	}
        private void setup() throws Exception {
                primaryStage.setTitle("Status Page");
		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
                barGraph = new Chart(db);

                ArrayList<YearData> locs = new DataCreator(db).generateYearData(); 

                barGraph.getCanvas(locs, "line");

                pane2 = new BorderPane();
                scene = new Scene(pane2, 800, 600);
		
                VBox windowTopBox = new VBox();
                
                bar = new WindowToolbar(
                        scene, primaryStage, db);
                bar.setToolbar(toolbar);

                pane2.setCenter(pane);
                windowTopBox.getChildren().addAll(bar, toolbar);
                pane2.setTop(windowTopBox);
                
		Text sceneTitle = new Text("Current Status");
		
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		pane.add(sceneTitle, 0, 0, 2, 1);
		
		table = new TableView();
		table.setPrefHeight(500);
                table.setEditable(true);
		TableColumn employeeIDColumn = new TableColumn("Employee ID");
                employeeIDColumn.setCellValueFactory(
                    new PropertyValueFactory<>("ID"));
		employeeIDColumn.setPrefWidth(100);
		TableColumn locationColumn = new TableColumn("Current Location");
		locationColumn.setPrefWidth(200);
                locationColumn.setCellValueFactory(
                    new PropertyValueFactory<>("Location"));                
		TableColumn typeOfWorkColumn = new TableColumn("Work being Performed");
		typeOfWorkColumn.setPrefWidth(150);
                typeOfWorkColumn.setCellValueFactory(
                    new PropertyValueFactory<>("Type")); 
		TableColumn nextAppointmentColumn = new TableColumn("Next Appointment Time");
		nextAppointmentColumn.setPrefWidth(200);
                nextAppointmentColumn.setCellValueFactory(
                    new PropertyValueFactory<>("Appointment"));                 
		table.getColumns().addAll(employeeIDColumn, locationColumn, typeOfWorkColumn, nextAppointmentColumn);
		
                tabSetup();
                final VBox vbox = new VBox();
		vbox.setPrefWidth(700);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0 , 0, 10));
		vbox.getChildren().addAll(statusPane);
		pane.add(vbox, 0, 1);   
        }
        public void update() throws Exception {
            
            try {
                ResultSet locResults = db.selectDataColumn("technician", "Location");
                ResultSet typeResults = db.selectDataColumn("technician", "type");
                ResultSet appointmentResults = db.selectDataColumn("technician", "Appointment");
                ResultSet techIDResults = db.selectDataColumn("technician", "TechID");
                List<StatusEntry> entryUpdate = new ArrayList<>();
                    while(locResults.next() && typeResults.next() && appointmentResults.next()
                                && techIDResults.next()) {
                        try {
                            try{
                            StatusEntry entry = new StatusEntry(techIDResults.getInt(1) + "", locResults.getString(1), typeResults.getString(1),  appointmentResults.getString(1));

                            entryUpdate.add(entry);
                            }catch(Exception e) {
                                StatusEntry entry = new StatusEntry(techIDResults.getInt(1) + "", locResults.getString(1), "none",  "none");
                                entryUpdate.add(entry);
                            }
                        }catch(Exception e) {
                        }
                    }
                table.setMinHeight(200);
                entries.removeAll(entries);
                entries.addAll(entryUpdate);
                table.setItems(entries);
                }catch(Exception e){
                    System.out.println("1fail");
                }
      
        }
	private void tabSetup() {
            statusPane = new TabPane();
            JobsTable jobTable = new JobsTable(db);
            try {
                jobTable.update();
            } catch (Exception ex) {
                Logger.getLogger(StatusPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            jobTab = new Tab("Jobs");
            jobTab.setClosable(false);
            jobTab.setContent(jobTable.getTable());
            technicianTab = new Tab("Technicians");
            technicianTab.setContent(table);
            technicianTab.setClosable(false);
            customerTab = new Tab("Customer");
            CustomerTable customerTable = new CustomerTable(db);
            try {
                customerTable.update();
            } catch (Exception ex) {
                Logger.getLogger(StatusPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            customerTab.setClosable(false);
            customerTab.setContent(customerTable.getTable());
            graphTab = new Tab("Chart");
            graphTab.setClosable(false);
           
            VBox chartBox = new VBox();
            chartBox.getChildren().add(barGraph.getCanvas());
            
            graphTab.setContent(chartBox);
            toolbar.setTables(customerTable, jobTable, this);
            statusPane.getTabs().addAll(jobTab, technicianTab, customerTab, graphTab);
            
        }
}
