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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;


public class CustomerForm  extends TransitionScene{

        final TextField emailField = new TextField();
        final TextField telephoneField = new TextField();
        final TextField zipField = new TextField();
        final TextField fnameField = new TextField();
	final TextField lnameField = new TextField();
        final TextField addressField = new TextField();
        final TextField cityField = new TextField();
        final TextField stateField = new TextField();
        private WindowTools toolbar;
        private WindowToolbar bar;
        VBox windowTopBox = new VBox();
        
        public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Customer Entry Form");
		BorderPane pane2 = new BorderPane();
                GridPane pane = new GridPane();
                
                pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		Scene scene = new Scene(pane2, 600, 600);
		
                
                toolbar = new WindowTools();
                bar = new WindowToolbar(scene, primaryStage);
                windowTopBox.getChildren().addAll(bar, toolbar);
                pane2.setTop(windowTopBox);
                
                
		Text sceneTitle = new Text("Enter Customer Information:");
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,20));
		pane.add(sceneTitle, 0, 0, 2, 1);
		Label firstName = new Label("First Name:");
		pane.add(firstName, 0, 1);
		
		pane.add(fnameField, 1, 1);
		Label lastName = new Label("Last Name:");
		pane.add(lastName, 0, 2);
		
		pane.add(lnameField, 1, 2);
		Label address = new Label("Address:");
		pane.add(address, 0, 3);
		
		pane.add(addressField, 1, 3);
		Label city = new Label("City:");
		pane.add(city, 0, 4);
		
		pane.add(cityField, 1, 4);
		Label state = new Label("State:");
		pane.add(state, 0, 5);
		
		pane.add(stateField, 1, 5);
		Label zip = new Label("Zip:");
		pane.add(zip, 0, 6);
		
		pane.add(zipField, 1, 6);
		Label telephone = new Label("Telephone:");
		pane.add(telephone, 0, 7);
		
		pane.add(telephoneField, 1, 7);
		Label email = new Label("E-Mail:");
		pane.add(email, 0, 8);
		
		pane.add(emailField, 1, 8);
		
		Button submitButton = new Button("Submit Info");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().add(submitButton);
		pane.add(hbox, 1, 9);
		
		
		final Text submitMessage = new Text();
		pane.add(submitMessage, 1, 10);
		//HBox box = new HBox();
                //box.getChildren().add(pane);
                pane2.setCenter(pane);
                
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			
			
			@Override
			public void handle(ActionEvent event) {
				// TODO : Add if statement to test for each TextField
                            String fname = fnameField.getText();
                String lname = lnameField.getText();
                String address = addressField.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                String zip = zipField.getText();
                String telephone = telephoneField.getText();
                String email = emailField.getText();
                Label errormsg = new Label("Fill out all");
                
                try {
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmsDatabase", "root", "");
                    Statement myStatement = myConn.createStatement();
                    String sql = "insert into customer "
                    + " (cfName, clName, cstreetAddy, cCity, cZip, cState, cPhone)"
                    + " values";
                    myStatement.executeUpdate(sql);
                    System.out.println("New Customer has been successfully added");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(CustomerForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //Check and Validate that all fields are entered
                if(fname.equals("") || lname.equals("") || address.equals("") || city.equals("") || state.equals("") || zip.equals("") ||
                        telephone.equals("") || email.equals("")) {
                        final Popup popup = new Popup(); 
                        popup.setX(300); popup.setY(200);
                        popup.getContent().add(new HBox(8));
                        HBox hbox = new HBox(8); // spacing = 8
                        hbox.getChildren().add(errormsg);
                        popup.show(primaryStage);
                        submitMessage.setText("Successfully submitted Customer Information");
			}
                    }
		});
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
}
