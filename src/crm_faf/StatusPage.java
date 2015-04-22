
package crm_faf;
	
import Data.DataCreator;
import Data.DbConnection;
import Data.Location;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;


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
                scene = new Scene(pane2, 1400, 740);
		
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
                
                TableColumn employeeFirstNameColumn = new TableColumn("Employee First Name");
                employeeFirstNameColumn.setCellValueFactory(
                    new PropertyValueFactory<WidgetEntry,String>("FirstName"));
		employeeFirstNameColumn.setPrefWidth(150);
                
                TableColumn employeeLastNameColumn = new TableColumn("Employee Last Name");
                employeeLastNameColumn.setCellValueFactory(
                    new PropertyValueFactory<WidgetEntry,String>("LastName"));
		employeeLastNameColumn.setPrefWidth(150);
                
		TableColumn currentLocationColumn = new TableColumn("Current Location");
                currentLocationColumn.setCellValueFactory(
                    new PropertyValueFactory<WidgetEntry,String>("Location"));
                currentLocationColumn.setPrefWidth(200);
                
		TableColumn typeOfWorkColumn = new TableColumn("Type of Work");
                typeOfWorkColumn.setCellValueFactory(
                    new PropertyValueFactory<WidgetEntry,String>("Type"));
                typeOfWorkColumn.setPrefWidth(100);
                
                TableColumn nextAppointmentDateColumn = new TableColumn("Next Appointment Date");
                nextAppointmentDateColumn.setCellValueFactory(
                    new PropertyValueFactory<WidgetEntry,String>("AppointmentDate"));  
                nextAppointmentDateColumn.setPrefWidth(180);
                
		TableColumn nextAppointmentTimeColumn = new TableColumn("Next Appointment Time");
                nextAppointmentTimeColumn.setCellValueFactory(
                    new PropertyValueFactory<WidgetEntry,String>("AppointmentTime"));  
                nextAppointmentTimeColumn.setPrefWidth(180);
                
                TableColumn nextAppointmentLocationColumn = new TableColumn("Next Appointment Location");
                nextAppointmentLocationColumn.setCellValueFactory(
                    new PropertyValueFactory<WidgetEntry,String>("AppointmentLocation"));  
                nextAppointmentLocationColumn.setPrefWidth(230);
                
		table.getColumns().addAll(employeeIDColumn, employeeFirstNameColumn, employeeLastNameColumn, 
                        currentLocationColumn, typeOfWorkColumn, nextAppointmentDateColumn, nextAppointmentTimeColumn, nextAppointmentLocationColumn);
		
                HBox hbox = new HBox();
                final TextField employeeFirstNameField = new TextField();
                employeeFirstNameField.setPromptText("Employee First Name");
                employeeFirstNameField.setMaxWidth(employeeFirstNameColumn.getPrefWidth());
                
                final TextField employeeLastNameField = new TextField();
                employeeLastNameField.setPromptText("Employee Last Name");
                employeeLastNameField.setMaxWidth(employeeLastNameColumn.getPrefWidth());
                
                final TextField currentLocationField = new TextField();
                currentLocationField.setPromptText("Current Location");
                currentLocationField.setPrefWidth(250);
                
                final TextField typeOfWorkField = new TextField();
                typeOfWorkField.setPromptText("Type of Work");
                typeOfWorkField.setMaxWidth(employeeFirstNameColumn.getPrefWidth());
                
                final DatePicker nextAppointmentDatePicker = new DatePicker();
                nextAppointmentDatePicker.setPromptText("Next Appointment Date");
                nextAppointmentDatePicker.setPrefWidth(190);
                
                final TextField nextAppointmentTimeField = new TextField();
                nextAppointmentTimeField.setPromptText("Next Appointment Time");
                nextAppointmentTimeField.setMaxWidth(nextAppointmentTimeColumn.getPrefWidth());
                
                final TextField nextAppointmentLocationField = new TextField();
                nextAppointmentLocationField.setPromptText("Next Appointment Location");
                nextAppointmentLocationField.setPrefWidth(250);
                
                hbox.getChildren().addAll(employeeFirstNameField, employeeLastNameField, currentLocationField,
                        typeOfWorkField, nextAppointmentDatePicker, nextAppointmentTimeField, nextAppointmentLocationField);
                
                Button addNewStatusButton = new Button();
                addNewStatusButton.setText("Add New Status");
                addNewStatusButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if(employeeFirstNameField.getText().equals("") || employeeLastNameField.getText().equals("") || 
                           currentLocationField.getText().equals("")|| typeOfWorkField.getText().equals("") ||
                                nextAppointmentTimeField.getText().equals("")
                                || nextAppointmentLocationField.getText().equals("")) {
                            System.out.println("Please make sure all fields have been entered");
                            //notesItems.add(notesTitleField.getText());
                            //entryData.add(employeeFirstNameField.getText());
                            
                        } else {
                            System.out.println("Enter New Status!");
                        }
                    }
                });
                
                Button clearButton = new Button();
                clearButton.setText("Clear Fields");
                clearButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        employeeFirstNameField.clear();
                        employeeLastNameField.clear();;
                        currentLocationField.clear();
                        typeOfWorkField.clear();
                        
                        nextAppointmentTimeField.clear();
                        nextAppointmentLocationField.clear();
                    }
                });
                
                final VBox vbox = new VBox();
		vbox.setPrefWidth(1300);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0 , 0, 10));
		vbox.getChildren().addAll(addNewStatusButton, clearButton, hbox, table,lineGraph.getCanvas(),barGraph.getCanvas(),pieGraph.getCanvas());
		pane.add(vbox, 0, 1);   
                
        }
        public void update() throws Exception {
            DbConnection db = new DbConnection();
            
            try {
                db.connect();
                ResultSet locResults = db.selectDataColumn("technician", "Location", "1");
                ResultSet typeResults = db.selectDataColumn("technician", "type", "1");
                ResultSet appointmentResults = db.selectDataColumn("technician", "Appointment", "1");
                ResultSet techIDResults = db.selectDataColumn("technician", "TechID", "1");
                
                List<StatusEntry> entryUpdate = new ArrayList<StatusEntry>();
                    while(locResults.next() && typeResults.next() && appointmentResults.next()
                                && techIDResults.next()) {
                        System.out.println(locResults.getString(1));
                        System.out.println(typeResults.getString(1));
                        System.out.println(appointmentResults.getDate(1));
                        System.out.println(techIDResults.getInt(1) + locResults.getString(1) + typeResults.getString(1) +  appointmentResults.getDate(1));
                        StatusEntry entry = new StatusEntry(techIDResults.getInt(1) + "", locResults.getString(1), typeResults.getString(1),  appointmentResults.getString(1));
                        System.out.println("entry" + entry.toString());
                        entryUpdate.add(entry);
                    }
                entries.removeAll(entries);
                entries.addAll(entryUpdate);
                table.setItems(entries);
                System.out.println(entries.size());
                table.setMinHeight(200.5);
                table.autosize();
                }catch(Exception e){System.out.println("1fail");};
      
        }
	
}
