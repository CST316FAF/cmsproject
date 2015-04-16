package crm_faf;
	
import Data.DataCreator;
import Data.DbConnection;
import Data.Location;
import DataCharts.Chart;
import java.util.ArrayList;
import javafx.application.Application;
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
import javafx.scene.control.ScrollPane;
import com.zenjava.jfxflow.actvity.AbstractActivity;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


public class StatusPage extends TransitionScene {
        private WindowTools toolbar;
        private WindowToolbar bar;
        private  TableView table;
        private  List<StatusEntry> entryData = new ArrayList<StatusEntry>();
        private  ObservableList<StatusEntry> entries = FXCollections.observableList(entryData);
        private Scene scene;
        private BorderPane pane2;
        private Stage primaryStage;
        private GridPane pane;
        
	public Scene start(Stage primaryStage, WindowTools tBar) {
                this.toolbar = tBar;
		this.primaryStage = primaryStage;
                setup();
                //AppointmentNotifications notify = new AppointmentNotifications();
                //notify.newMessage();
	
		primaryStage.setScene(scene);
		primaryStage.show();
            try {
                this.update();
            } catch (Exception ex) {
                Logger.getLogger(StatusPage.class.getName()).log(Level.SEVERE, null, ex);
            }
		return scene;
	}
        private void setup() {
                primaryStage.setTitle("Status Page");
		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
                Chart lineGraph = new Chart();
                Chart barGraph = new Chart();
                Chart pieGraph = new Chart();

                ArrayList<Location> locs = new DataCreator().createLocations(); 

                lineGraph.getCanvas(locs, "bar");
                lineGraph.getCanvas(locs, "bar");
                barGraph.getCanvas(locs, "pie");
                pieGraph.getCanvas(locs, "line");

                pane2 = new BorderPane();
                scene = new Scene(pane2, 800, 600);
		
                VBox windowTopBox = new VBox();
                
                bar = new WindowToolbar(lineGraph.getCanvas(), 
                        barGraph.getCanvas(), pieGraph.getCanvas(),
                        scene, primaryStage);
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
                    new PropertyValueFactory<WidgetEntry,String>("ID"));
		employeeIDColumn.setPrefWidth(100);
		TableColumn locationColumn = new TableColumn("Current Location");
		locationColumn.setPrefWidth(200);
                locationColumn.setCellValueFactory(
                    new PropertyValueFactory<WidgetEntry,String>("Location"));                
		TableColumn typeOfWorkColumn = new TableColumn("Work being Performed");
		typeOfWorkColumn.setPrefWidth(150);
                typeOfWorkColumn.setCellValueFactory(
                    new PropertyValueFactory<WidgetEntry,String>("Type")); 
		TableColumn nextAppointmentColumn = new TableColumn("Next Appointment Time");
		nextAppointmentColumn.setPrefWidth(200);
                nextAppointmentColumn.setCellValueFactory(
                    new PropertyValueFactory<WidgetEntry,String>("Appointment"));                 
		table.getColumns().addAll(employeeIDColumn, locationColumn, typeOfWorkColumn, nextAppointmentColumn);
		
                
                final VBox vbox = new VBox();
		vbox.setPrefWidth(700);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0 , 0, 10));
		vbox.getChildren().addAll(table,lineGraph.getCanvas(),barGraph.getCanvas(),pieGraph.getCanvas());
		pane.add(vbox, 0, 1);   
        }
        public void update() throws Exception {
            DbConnection db = new DbConnection();
            
            try {
                db.connect();
                ResultSet locResults = db.selectDataColumn("technician", "Location", "1");
                ResultSet typeResults = db.selectDataColumn("technician", "type", "1");
                ResultSet appointmentResults = db.selectDataColumn("technician", "Appointment", "1");
                List<StatusEntry> entryUpdate = new ArrayList<StatusEntry>();
                    while(locResults.next() && typeResults.next() && appointmentResults.next()) {
                        System.out.println(locResults.getString(1));
                        System.out.println(typeResults.getString(1));
                        System.out.println(appointmentResults.getDate(1));
                        System.out.println("Need to change back" + locResults.getString(1) + typeResults.getString(1) +  appointmentResults.getDate(1));
                        StatusEntry entry = new StatusEntry("Need to change back", locResults.getString(1), typeResults.getString(1),  appointmentResults.getString(1));
                        System.out.println("entry" + entry.toString());
                        entryUpdate.add(entry);
                    }
                entries.removeAll(entries);
                entries.addAll(entryUpdate);
                table.setItems(entries);
                System.out.println(entries.size());
                table.autosize();
                }catch(Exception e){System.out.println("1fail");};
      
        }
	
}