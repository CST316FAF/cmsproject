package crm_faf;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class CustomerForm  {

	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Customer Entry Form");
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		Scene scene = new Scene(pane, 600, 400);
		
		Text sceneTitle = new Text("Enter Customer Information:");
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,20));
		pane.add(sceneTitle, 0, 0, 2, 1);
		Label firstName = new Label("First Name:");
		pane.add(firstName, 0, 1);
		final TextField fnameField = new TextField();
		pane.add(fnameField, 1, 1);
		Label lastName = new Label("Last Name:");
		pane.add(lastName, 0, 2);
		final TextField lnameField = new TextField();
		pane.add(lnameField, 1, 2);
		Label address = new Label("Address:");
		pane.add(address, 0, 3);
		final TextField addressField = new TextField();
		pane.add(addressField, 1, 3);
		Label city = new Label("City:");
		pane.add(city, 0, 4);
		final TextField cityField = new TextField();
		pane.add(cityField, 1, 4);
		Label state = new Label("State:");
		pane.add(state, 0, 5);
		final TextField stateField = new TextField();
		pane.add(stateField, 1, 5);
		Label zip = new Label("Zip:");
		pane.add(zip, 0, 6);
		final TextField zipField = new TextField();
		pane.add(zipField, 1, 6);
		Label telephone = new Label("Telephone:");
		pane.add(telephone, 0, 7);
		final TextField telephoneField = new TextField();
		pane.add(telephoneField, 1, 7);
		Label email = new Label("E-Mail:");
		pane.add(email, 0, 8);
		final TextField emailField = new TextField();
		pane.add(emailField, 1, 8);
		
		Button submitButton = new Button("Submit Info");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().add(submitButton);
		pane.add(hbox, 1, 9);
		
		
		final Text submitMessage = new Text();
		pane.add(submitMessage, 1, 10);
		
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			
			
			@Override
			public void handle(ActionEvent event) {
				// TODO : Add if statement to test for each TextField
				submitMessage.setText("Successfully submitted Customer Information");
			}
			
		});
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
}
