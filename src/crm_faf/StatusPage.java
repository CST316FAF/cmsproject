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
		this.primaryStage = primaryStage;
		
		
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
		
		TableView table = new TableView();
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
            db.connect();
            ResultSet locResults = db.selectDataColumn("technician", "Location", "1");
            ResultSet IdResults = db.selectDataColumn("technician", "techID", "1");
            ResultSet TypeResults = db.selectDataColumn("technician", "Type", "1");
            ResultSet AppointmentResults = db.selectDataColumn("technician", "Appointment", "1");
            ArrayList<String> list = new ArrayList<String>();
            System.out.println(IdResults.first());
            try{
            System.out.println(locResults.first());
            System.out.printf(locResults.getCharacterStream("Location").getClass().toString());
            }catch(Exception e){System.out.println("1fail");};
            
            try{
            AppointmentResults.getDate("Appointment");
            }catch(Exception e){System.out.println("2fail");};
            
            
            try{
            locResults.getNCharacterStream("Location");
            }catch(Exception e){System.out.println("3fail");};
            
            try{
            locResults.getCharacterStream("Location");
            }catch(Exception e){System.out.println("4fail");};
            
            System.out.println(IdResults.getShort("tech_ID"));
     //       System.out.println(locResults.getBytes("Location"));
       //     System.out.println(TypeResults.getBytes("Type"));
            System.out.println(AppointmentResults.getDate("Appointment"));
//            while (locResults.next() ) { 
//               list.add(locResults.getString("Location"));
//               StatusEntry entry = new StatusEntry(locResults.getString("Location"), 
//                       ("" + IdResults.getInt("techID")), TypeResults.getString("Type"), 
//                       AppointmentResults.getString("Appointment"));
//            } 
            System.out.print(locResults.toString());
           // for(int x = 0; x <)
           // entryData.addAll(data);
        }
	
}