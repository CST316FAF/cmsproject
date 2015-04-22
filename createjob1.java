package application;

import java.sql.Connection;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;


public class Main extends Application {
	
	TextArea jobDescriptionTA;

	Label techLabel, custLabel, tempLabel;
	
	ComboBox<String> techniciansCB;
	ComboBox<String> customersCB;
	
	String[] technicians = new String[] {"Tech1","Tech2","Tech3","Tech4"};
	String[] customers = new String[] {"Cust1","Cust2","Cust3","Cust4"};

	DatePicker datePicker;
	
	Button addJobButton;
	TableView jobs;
	
	final int WIN_W = 800;// window width
	final int WIN_H = 500;// window height

	

	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			Label descriptionLabel = new Label("Job Description");
			
			jobDescriptionTA = new TextArea();
			
			Label datePickerLabel = new Label("Date");
			
			datePicker = new DatePicker();
			
			
			
			addJobButton = new Button("Add Job");
			addJobButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
				
					tempLabel.setText("tech:  " + techniciansCB.getValue() + "\ncust:  " +  customersCB.getValue() + 
							"\ndescription:  " + jobDescriptionTA.getText() + "\ndate:  " + datePicker.getValue());
				}
			});
			
			tempLabel = new Label();
		
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	

	
}
