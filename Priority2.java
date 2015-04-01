package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
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
	TableView<Technician> technicians;
	TableView jobs;
	final int WIN_W = 800;// window width
	final int WIN_H = 800;// window height

	@Override
	public void start(Stage primaryStage) {
		try {
			 BorderPane root = new BorderPane();

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
			taskPriority.getItems().addAll("low", "medium", "high");
			taskPriority.setValue("low");
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
			
			Label techLabel = new Label("Technicians");
			
			technicians = new TableView();
			technicians.setPrefHeight(WIN_H/3);
			
			TableColumn nameCol = new TableColumn<Technician,String>("Name");
	        nameCol.setMinWidth(100);
	        nameCol.setCellValueFactory(
	          new PropertyValueFactory<Technician, String>("name"));
			
			TableColumn phoneCol = new TableColumn<Technician,String>("Phone");
			phoneCol.setMinWidth(100);
			phoneCol.setCellValueFactory(
	            new PropertyValueFactory<Technician, String>("phone"));
			
			TableColumn emailCol = new TableColumn<Technician,String>("Email");
			emailCol.setMinWidth(100);
			emailCol.setCellValueFactory(
	            new PropertyValueFactory<Technician, String>("email"));
			
			TableColumn whCol = new TableColumn<Technician,String>("Work Hours");
			whCol.setMinWidth(100);
			whCol.setCellValueFactory(
	            new PropertyValueFactory<Technician, String>("workHours"));
			
			ObservableList<Technician> data =  FXCollections.observableArrayList(new Technician("Tech1","123-456-7890","a@b.com","10"));
			data.add(new Technician("Tech2","111-222-3333","b@c.com","20"));
			data.add(new Technician("Tech3","000-222-5677","b@c.com","5"));
			data.add(new Technician("Tech4","666-222-5555","f@l.com","100"));
			
			technicians.setItems(data);
			
			technicians.getColumns().addAll(nameCol,phoneCol,emailCol,whCol);
			
			Label jobsLabel = new Label("Tasks");
			
			jobs = new TableView();
			jobs.setPrefHeight(WIN_H/3);
			
			TableColumn jobNumberCol = new TableColumn<Technician,String>("Job #");
			jobNumberCol.setMinWidth(100);
			jobNumberCol.setCellValueFactory(
	           new PropertyValueFactory<Job, String>("number"));
			
			TableColumn priorityCol = new TableColumn<Technician,String>("Priority");
			priorityCol.setMinWidth(100);
		priorityCol.setCellValueFactory(
	          new PropertyValueFactory<Job, String>("priority"));
			
			TableColumn custNameCol = new TableColumn<Technician,String>("Customer Name");
			custNameCol.setMinWidth(100);
			custNameCol.setCellValueFactory(
	           new PropertyValueFactory<Job, String>("priority"));
			
			TableColumn techNameCol = new TableColumn<Technician,String>("Technician Name");
			techNameCol.setMinWidth(100);
			techNameCol.setCellValueFactory(
	            new PropertyValueFactory<Job, String>("priority"));
			
			ObservableList<Job> jobData =  FXCollections.observableArrayList(new Job("0000","high"));
			jobData.add(new Job("0001","low"));
			jobData.add(new Job("0001","medium"));
			jobData.add(new Job("0001","low"));
			jobData.add(new Job("0001","medium"));

		jobs.setItems(jobData);
			
			jobs.getColumns().addAll(jobNumberCol,priorityCol,custNameCol,techNameCol);
			
			rootPane.getChildren().addAll(taskLabel, top, custLabel, middle,jobsLabel, jobs);

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
	
	public static class Technician {
	    private final SimpleStringProperty name;
	    private final SimpleStringProperty phone;
	    private final SimpleStringProperty email;
	    private final SimpleStringProperty workHours;
	    
	    private Technician(String name, String phone, String email, String workHours) {
	        this.name = new SimpleStringProperty(name);
	        this.phone = new SimpleStringProperty(phone);
	        this.email = new SimpleStringProperty(email);
	        this.workHours = new SimpleStringProperty(workHours);
	    }	 
	    public String getName() {
	        return name.get();
	    }
	    public void setName(String name) {
	        this.name.set(name);
	    }
	    public String getPhone() {
	        return phone.get();
	    }
	    public void setPhone(String phone) {
	        this.phone.set(phone);
	    }
	    public String getEmail() {
	        return email.get();
	    }
	    public void setEmail(String email) {
	        this.email.set(email);
	    }
	    public String getWorkHours() {
	        return workHours.get();
	    }
	    public void setWorkHours(String workHours) {
	        this.workHours.set(workHours);
	    }	        
	}
	
	public static class Job {
	    private final SimpleStringProperty number;
	    private final SimpleStringProperty priority;
	    
	    private Job(String number, String priority) {
	        this.number= new SimpleStringProperty(number);
	        this.priority = new SimpleStringProperty(priority);
	    }	 
	    public String getNumber() {
	        return number.get();
	    }
	    public void setNumber(String number) {
	        this.number.set(number);
	    }
	    public String getPriority() {
	        return priority.get();
	    }
	    public void setPriority(String priority) {
	        this.priority.set(priority);
	    }
	}
	
}
