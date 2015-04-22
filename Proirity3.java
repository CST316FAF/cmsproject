package application;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class Main extends Application {

	TextField taskNumber, taskStatus, taskName, serviceRequest, custCity,
			taskType, taskUrgency, custNumber, custAccount,custName,custCode,
			custType;
	ComboBox<String> taskPriority;
	CheckBox invalidAddress, schedulable;
	Button addTaskButton;
	TableView tasks;
	ObservableList<Task> taskData;
	final int WIN_W = 800;// window width
	final int WIN_H = 500;// window height
	
	//SQL Stuff
	static final String DB_URL = "jdbc:mysql://localhost:3306/database1";
	public static final String USER = "root";
	public static final String PASSWORD = "Welcome1";
	private final String TABLE_NAME = "PRIORITYORDER";
	String driverName = "com.mysql.jdbc.Driver"; // for MySql
	Connection conn = null;
	

	@Override
	public void start(Stage primaryStage) {
			// BorderPane root = new BorderPane();

			Label tempLabel;

			VBox rootPane = new VBox();

			Label taskLabel = new Label("Task");

			HBox top = new HBox();
			top.setBorder(new Border(new BorderStroke[] { new BorderStroke(
					Color.GRAY, BorderStrokeStyle.SOLID, null, null) }));

			VBox topLeft = new VBox();
			topLeft.setPrefWidth(WIN_W / 3);

			HBox taskNumberBox = new HBox();
			tempLabel = new Label("Number");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			taskNumber = new TextField();
			taskNumberBox.getChildren().addAll(tempLabel, taskNumber);

			HBox taskStatusBox = new HBox();
			tempLabel = new Label("Status");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			taskStatus = new TextField();
			taskStatusBox.getChildren().addAll(tempLabel, taskStatus);

			invalidAddress = new CheckBox("Invalid Address");

			topLeft.getChildren().addAll(taskNumberBox, taskStatusBox,
					invalidAddress);

			VBox topMiddle = new VBox();
			topMiddle.setPrefWidth(WIN_W / 3);

			HBox taskNameBox = new HBox();
			tempLabel = new Label("Name");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			taskName = new TextField();
			taskNameBox.getChildren().addAll(tempLabel, taskName);

			HBox priorityBox = new HBox();
			tempLabel = new Label("Priority");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			taskPriority = new ComboBox<String>();
			taskPriority.getItems().addAll("1. high", "2. medium", "3. low");
			taskPriority.setValue("1. high");
			priorityBox.getChildren().addAll(tempLabel, taskPriority);

			HBox serviceRequestBox = new HBox();
			tempLabel = new Label("Service Request");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			serviceRequest = new TextField();
			serviceRequestBox.getChildren().addAll(tempLabel, serviceRequest);

			topMiddle.getChildren().addAll(taskNameBox, priorityBox,
					serviceRequestBox);

			VBox topRight = new VBox();

			topRight.setPrefWidth(WIN_W / 3);

			HBox taskTypeBox = new HBox();
			tempLabel = new Label("Type");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			taskType = new TextField();
			taskTypeBox.getChildren().addAll(tempLabel, taskType);

			HBox taskUrgencyBox = new HBox();
			tempLabel = new Label("Urgency");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			taskUrgency = new TextField();
			taskUrgencyBox.getChildren().addAll(tempLabel, taskUrgency);

			schedulable = new CheckBox("Schedulable");

			topRight.getChildren().addAll(taskTypeBox, taskUrgencyBox,
					schedulable);

			top.getChildren().addAll(topLeft, topMiddle, topRight);

			Label custLabel = new Label("Customer");
			
			HBox middle = new HBox();
			middle.setBorder(new Border(new BorderStroke[] { new BorderStroke(
					Color.GRAY, BorderStrokeStyle.SOLID, null, null) }));

			VBox middleLeft = new VBox();
			middleLeft.setPrefWidth(WIN_W / 3);

			HBox custNumberBox = new HBox();
			tempLabel = new Label("Number");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			custNumber = new TextField();
			custNumberBox.getChildren().addAll(tempLabel, custNumber);

			HBox custCityBox = new HBox();
			tempLabel = new Label("City");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			custCity = new TextField();
			custCityBox.getChildren().addAll(tempLabel, custCity);

			HBox custAccountBox = new HBox();
			tempLabel = new Label("Account");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			custAccount = new TextField();
			custAccountBox.getChildren().addAll(tempLabel, custAccount);
			
			middleLeft.getChildren().addAll(custNumberBox,custCityBox,custAccountBox);

			VBox middleMiddle = new VBox();
			middleMiddle.setPrefWidth(WIN_W / 3);

			HBox custNameBox = new HBox();
			tempLabel = new Label("Name");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			custName = new TextField();
			custNameBox.getChildren().addAll(tempLabel, custName);

			HBox custCodeBox = new HBox();
			tempLabel = new Label("Postal Code");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			custCode = new TextField();
			custCodeBox.getChildren().addAll(tempLabel, custCode);

			HBox custTypeBox = new HBox();
			tempLabel = new Label("Contract Type");
			tempLabel.setPrefWidth(WIN_W / 3 / 3);
			custType = new TextField();
			custTypeBox.getChildren().addAll(tempLabel, custType);
			
			middleMiddle.getChildren().addAll(custNameBox,custCodeBox,custTypeBox);
			
			VBox middleRight = new VBox();
			
			//Time zone stuff here?
			
			middle.getChildren().addAll(middleLeft,middleMiddle);
			
			Label tasksLabel = new Label("Tasks");
			
			tasks = new TableView();
			tasks.setPrefHeight(WIN_H/3);
			
			TableColumn TaskNumberCol = new TableColumn<Task,String>("Task #");
			TaskNumberCol.setMinWidth(150);
			TaskNumberCol.setCellValueFactory(
	            new PropertyValueFactory<Task, String>("taskNumber"));
			
			TableColumn priorityCol = new TableColumn<Task,String>("Priority");
			priorityCol.setSortType(TableColumn.SortType.ASCENDING);
			priorityCol.setMinWidth(150);
			priorityCol.setCellValueFactory(
	            new PropertyValueFactory<Task, String>("priority"));
			
			TableColumn custNameCol = new TableColumn<Task,String>("Customer Name");
			custNameCol.setMinWidth(150);
			custNameCol.setCellValueFactory(
	            new PropertyValueFactory<Task, String>("customerName"));
			
			TableColumn techNameCol = new TableColumn<Task,String>("Technician Name");
			techNameCol.setMinWidth(150);
			techNameCol.setCellValueFactory(
	            new PropertyValueFactory<Task, String>("technicianName"));
			
			ObservableList<Task> taskData =  FXCollections.observableArrayList();
			

			ResultSet rs = null;
			conn = null;
			try {

				Class.forName(driverName).newInstance();
				conn = DriverManager.getConnection(DB_URL, USER,PASSWORD);
				Statement stmt = conn.createStatement();

				/* For debugging and testing: Drops (deletes) a database.
				 * String drop = "drop TABLE "+TABLE_NAME+"";
				 * stmt.executeUpdate(drop);
				 */

				//Check to see if database already exists
				DatabaseMetaData md = conn.getMetaData();
				rs = md.getTables(null, null, TABLE_NAME, null);
				int count = 0;
				while (rs.next()) {
					count++;
				}
				
				// Database does not exist so create one
				if (count == 0) {
					System.out.println("creating new table since it doens't exist");
					String sql = "CREATE TABLE "+TABLE_NAME+" (number VARCHAR(255) NOT NULL, priority VARCHAR(255),customer VARCHAR(255), technician VARCHAR(255), PRIMARY KEY (number))";
					stmt.executeUpdate(sql);
				}else {
					System.out.println("table already exists, no need to create new");
				}

				// SQL FOR SELECTING ALL OF CUSTOMER
				String SQL = "SELECT * from "+TABLE_NAME;
				// ResultSet
				rs = conn.createStatement().executeQuery(SQL);
				
				while (rs.next()) {
					System.out.println("adding "  + rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4));
					taskData.add(new Task(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
					
				}

			tasks.setItems(taskData);
			
			tasks.getColumns().addAll(TaskNumberCol,priorityCol,custNameCol,techNameCol);
			
			tasks.getSortOrder().add(priorityCol);
			
			tasks.sort();
			
			addTaskButton = new Button("Add Task");
			addTaskButton.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					//add the new data for the task to the table's data
					Task taskToAdd = new Task(taskNumber.getText(),taskPriority.getValue(), taskName.getText(),custName.getText());
					taskData.add(taskToAdd);
					
					//SQL for adding new technician entry in database
					
					conn = null;
					try {

						Class.forName(driverName).newInstance();
						conn = DriverManager.getConnection(DB_URL, USER,PASSWORD);
						Statement stmt = conn.createStatement();
					
						 String newEntryStatement = "INSERT INTO "+TABLE_NAME+" VALUES ('"+ taskNumber.getText() + "','" +
								 taskPriority.getValue() + "','" + taskName.getText() + "','" + custName.getText() + "')";
						 
						 stmt.executeUpdate(newEntryStatement);
					}catch(Exception ee) {
						System.out.println("Exception: "+ ee.toString());
						
					}finally {
						
						if(conn != null) {
							try{
								conn.close();
							}catch(SQLException se) {System.out.println("could not close connection");}
						}
						
					}
					
					tasks.sort();
					taskNumber.clear();taskName.clear();custName.clear();
				}

			});
			
			
			rootPane.getChildren().addAll(taskLabel, top, custLabel, middle,addTaskButton,tasksLabel, tasks);

			Scene scene = new Scene(rootPane, WIN_W, WIN_H);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	
	public static class Task {
	    private final SimpleStringProperty taskNumber;
	    private final SimpleStringProperty priority;
	    private final SimpleStringProperty customerName;
	    private final SimpleStringProperty technicianName;
	    
	    private Task(String taskNumber, String priority, String customerName, String technicianName) {
	        this.taskNumber= new SimpleStringProperty(taskNumber);
	        this.priority = new SimpleStringProperty(priority);
	        this.customerName = new SimpleStringProperty(customerName);
	        this.technicianName = new SimpleStringProperty(technicianName);
	        
	    }	 
	    public String getTaskNumber() {
	        return taskNumber.get();
	    }
	    public void setTaskNumber(String taskNumber) {
	        this.taskNumber.set(taskNumber);
	    }
	    public String getPriority() {
	        return priority.get();
	    }
	    public void setPriority(String priority) {
	        this.priority.set(priority);
	    }
	    
	    public String getCustomerName() {
	        return customerName.get();
	    }
	    public void setCustomerName(String customerName) {
	        this.customerName.set(customerName);
	    }
	    public String getTechnicianName() {
	        return technicianName.get();
	    }
	    public void setTechnicianNameName(String technicianName) {
	        this.technicianName.set(technicianName);
	    }
	    
	}
	
}
