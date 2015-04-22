package application;

import javafx.application.Application;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// I did metrix for McCabe Cyclomatic Complexity. after I did Junit test, it is 1723.
public class Main extends Application {
	TextArea jobDescriptionTA;
// I created the gutter and setter for teach label and custlabel
	Label techLabel, custLabel, tempLabel;
	
	public Label getTechLabel() {
		return techLabel;
	}

	public void setTechLabel(Label techLabel) {
		this.techLabel = techLabel;
	}

	public Label getCustLabel() {
		return custLabel;
	}

	public void setCustLabel(Label custLabel) 
	{
		this.custLabel = custLabel;
	}

	ComboBox<String> techniciansCB;
	ComboBox<String> customersCB;
	
	String[] technicians = new String[] {"Tech1","Tech2","Tech3","Tech4"};
	String[] customers = new String[] {"Cust1","Cust2","Cust3","Cust4"};

	DatePicker datePicker;
	
	Button addJobButton;
	TableView jobs;

	// (PM) add static before final int 
	static final int WIN_W = 800;// window width
	static final int WIN_H = 500;// window height

	
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			
		    Label addJobScreenLabel = new Label("Add Job Screen");
			addJobScreenLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			HBox dropBoxesHB = new HBox();
			
			techLabel = new Label("Technician");
			double techLabelSize = TextUtils.computeTextWidth(techLabel.getFont(),techLabel.getText(),0.0D);
			techLabel.setPrefWidth(techLabelSize + 10);
			custLabel = new Label("Customer");
			double custLabelSize = TextUtils.computeTextWidth(custLabel.getFont(),custLabel.getText(),0.0D);
			System.out.println("Result: " + TextUtils.computeTextWidth(techLabel.getFont(),techLabel.getText(),0.0D));
			custLabel.setPrefWidth(custLabelSize + 10);
			
			techniciansCB = new ComboBox<String>();
			techniciansCB.setPrefWidth(   WIN_W / 2 - techLabel.getPrefWidth() - 15/2 );
			techniciansCB.getItems().addAll(technicians);
			techniciansCB.setValue("Choose a Technician");
			
			customersCB = new ComboBox<String>();
			customersCB.setPrefWidth(WIN_W / 2 - custLabel.getPrefWidth() - 15/2);
			customersCB.getItems().addAll(customers);
			customersCB.setValue("Choose a Customer");
				
			dropBoxesHB.setSpacing(5.0);
			
			dropBoxesHB.getChildren().addAll(techLabel, techniciansCB, custLabel, customersCB);
			
			HBox descriptionAndDateHB = new HBox();
			
			Label descriptionLabel = new Label("Job Description");
			
			jobDescriptionTA = new TextArea();
			
			Label datePickerLabel = new Label("Date");
			
			datePicker = new DatePicker();
			
			descriptionAndDateHB.setSpacing(5.0);
			
			descriptionAndDateHB.getChildren().addAll(descriptionLabel, jobDescriptionTA,datePickerLabel,datePicker);
			
			addJobButton = new Button("Add Job");
			addJobButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					tempLabel.setText("tech:  " + techniciansCB.getValue() + "\ncust:  " +  customersCB.getValue() + 
							"\ndescription:  " + jobDescriptionTA.getText() + "\ndate:  " + datePicker.getValue());
				}
			});
			
			tempLabel = new Label();
			
			root.setSpacing(20.0);
			
			root.getChildren().addAll(addJobScreenLabel,dropBoxesHB,descriptionAndDateHB,addJobButton,tempLabel);
			
			Scene scene = new Scene(root, WIN_W, WIN_H);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		//Devefensive programming
		// #3. The method does a poor job of exception handling. Catch each specific type
				//     of checked exception possible within this block, print out a message
				//     indicating the type of exception caught (e.g. "Caught XXX"), and rethrow a 
				//     new exception called MyDataInitializationException (you have to write this).
				// handled by changing the code 
		
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ExceptionInInitializerError();
		}
		
		//{
			//e.printStackTrace();
			
		//}
	}

	public static void main(String[] args) 
	
	{
		launch(args);
	}
	
	

	
}

