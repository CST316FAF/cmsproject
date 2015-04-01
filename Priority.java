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

	TableView jobs;
	final int WIN_W = 800;// window width
	final int WIN_H = 800;// window height

	@Override
	public void start(Stage primaryStage) {
		try {
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
			
		
			
		
			
			Label jobsLabel = new Label("Tasks");
			
			jobs = new TableView();
			jobs.setPrefHeight(WIN_H/3);
			
		
			
		

		
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
		
}
