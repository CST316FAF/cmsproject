package crm_faf;
	
import Data.DbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        final TextField customerId = new TextField();
        private WindowTools toolbar;
        private WindowToolbar bar;
        VBox windowTopBox = new VBox();
    
        ResultSet myResultSet;
        Statement myStatement;
        Stage primaryStage = new Stage();
    private DbConnection db;
        
        public void start(Stage primaryStage, DbConnection db) {
		this.db = db;
                primaryStage = this.primaryStage;
		primaryStage.setTitle("Customer Entry Form");
		BorderPane pane2 = new BorderPane();
                GridPane pane = new GridPane();
                
                pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		Scene scene = new Scene(pane2, 600, 600);
		
                
                toolbar = new WindowTools(db);
                bar = new WindowToolbar(scene, primaryStage, db);
                //windowTopBox.getChildren().addAll(bar, toolbar);
                pane2.setTop(windowTopBox);
                
                
		Text sceneTitle = new Text("Enter Customer Information:");
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,20));
		pane.add(sceneTitle, 0, 0, 2, 1);
                
                pane.add(fnameField, 1, 1);
		Label firstName = new Label("First Name:");
		pane.add(firstName, 0, 1);
		
		pane.add(lnameField, 1, 2);
		Label lastName = new Label("Last Name:");
		pane.add(lastName, 0, 2);
		
		pane.add(addressField, 1, 3);
		Label address = new Label("Address:");
		pane.add(address, 0, 3);
		
		pane.add(cityField, 1, 4);
		Label city = new Label("City:");
		pane.add(city, 0, 4);
		
		pane.add(stateField, 1, 5);
		Label state = new Label("State:");
		pane.add(state, 0, 5);
		
		pane.add(zipField, 1, 6);
		Label zip = new Label("Zip:");
		pane.add(zip, 0, 6);
		
		pane.add(telephoneField, 1, 7);
		Label telephone = new Label("Telephone:");
		pane.add(telephone, 0, 7);
		
		pane.add(emailField, 1, 8);
		Label email = new Label("E-Mail:");
		pane.add(email, 0, 8);
                
                pane.add(customerId, 1, 9);
		Label customerID = new Label("Custom ID:");
		pane.add(customerID, 0, 9);
		
		Button submitButton = new Button("Submit Info");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().add(submitButton);
		pane.add(hbox, 1, 10);
		
		
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
                            String customerID = customerId.getText();
                            String P_ID = db.getId();
                            Label errormsg = new Label("Fill out all");
             
                
                //Check and Validate that all fields are entered.
                if(fname.equals("") || lname.equals("") || address.equals("") || city.equals("") || state.equals("") || zip.equals("") ||
                        telephone.equals("") || email.equals("")) {
                    
                        final Popup popup = new Popup(); 
                        popup.setX(300); popup.setY(200);
                        popup.getContent().add(new HBox(8));
                        HBox hbox = new HBox(8); // spacing = 8
                        hbox.getChildren().add(errormsg);
                        //popup.show(primaryStage);
                        submitMessage.setText("Please fill in all fields.");
                        }
                
                        //Check if first name, last name, address, city, state, and email are no more than 30 characters.
			else if(fname.length() > 30 || lname.length() > 30 || address.length() > 30 || city.length() > 30 || state.length() > 30 || 
                        email.length() > 30) {
                            
                        final Popup popup = new Popup(); 
                        popup.setX(300); popup.setY(200);
                        popup.getContent().add(new HBox(8));
                        HBox hbox = new HBox(8); // spacing = 8
                        hbox.getChildren().add(errormsg);
                        //popup.show(primaryStage);
                        submitMessage.setText("Please do not exceed 30 characters in fields.");
                        } 
                         
                        //Check to make sure the zip is exactly 5 characters and telephone is exactly 10 characters.
                        else if(zip.length() != 5 || telephone.length() != 10) {
                            
                        final Popup popup = new Popup(); 
                        popup.setX(300); popup.setY(200);
                        popup.getContent().add(new HBox(8));
                        HBox hbox = new HBox(8); // spacing = 8
                        hbox.getChildren().add(errormsg);
                        //popup.show(primaryStage);
                        submitMessage.setText("Please make sure zip is exactly 5 digits \n and telephone is exactly 10 digits.");
                        } 
                         
                        //Check if the user has entered a number for zip and telephone fields.
                        else if(!isDigit(zip) && !isDigit(telephone)) {
                            
                        final Popup popup = new Popup(); 
                        popup.setX(300); popup.setY(200);
                        popup.getContent().add(new HBox(8));
                        HBox hbox = new HBox(8); // spacing = 8
                        hbox.getChildren().add(errormsg);
                        //popup.show(primaryStage);
                        submitMessage.setText("Please make sure zip and telephone are both numeric values");    
                        }
                
                
                //If all checks locally have passed make connection to database and make sure there
                // is no customer with the same first and last names in the database.
                //If there is no customer with the same first and last name then create 
                //the new customer and insert all their information into the database.
                        else {
                            try {
                                //DbConnection connection = new DbConnection();
                                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmsdb",
                                "root", "");
                                Statement myStatement = connection.createStatement();
                                String checkSQL = "select * from customer"; //where cfName = '"+fname+"'clName = '"+lname+"'";
                                       // + "cfName,clName from customer where cfName = '"+fname+"'clName = '"+lname+"'";
                                //PreparedStatement ps = connection.preparedStatement(checkSQL);
                                
                                ResultSet rs = myStatement.executeQuery(checkSQL);
                               
                                while(rs.next()) {
                                    System.out.println(rs.getString("P_ID") + ", " + rs.getString("clName"));
                                    if(rs.getString("customerID").equals(customerID)) {
                                        System.out.println("There is already a customer with that ID number");
                                    } else {
                                        System.out.println("Add the new customer");
                                        
                                        myStatement.execute("insert into customer (P_ID, CustomerID, cfName, clName, cstreetAddy, cCity, cZip, cState, cPhone, cEmail) "
                                        + "values ('"+P_ID+"','"+customerID+"','"+fname+"', '"+lname+"', '"+address+"', '"+city+"', '"+zip+"', '"+state+"', '"+telephone+"', '"+email+"' )");
                                        
                                        System.out.println("Successfully added customer!");
                                        fnameField.setText(" ");
                                        lnameField.setText(" ");
                                        addressField.setText(" ");
                                        cityField.setText(" ");
                                        stateField.setText(" ");
                                        zipField.setText(" ");
                                        telephoneField.setText(" ");
                                        emailField.setText(" ");
                                        customerId.setText(" ");
                                    }
                                    
                                } 
                               
                            } catch (SQLException ex) {
                                Logger.getLogger(CustomerForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         
                        }
                
                    }
            });
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
        //isDigit Method checks to see if entered parameter is an Int or not.
	public static boolean isDigit(String numberToCheck) {
            try {
                Integer.parseInt(numberToCheck);
                
            } catch (NumberFormatException e) {
                
                return false;
            }
            return true;
        }
        
        private int generateId() {
            int idNum = -1;
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmsdb",
                                    "root", "");
                Random rand = new Random();
                boolean generated = false;
                idNum = rand.nextInt((1000 - 1) + 1) + 1;
            
                while(!generated) {
                    Statement myStatement = connection.createStatement();
                    ResultSet rs = myStatement.executeQuery("SELECT 1 FROM customer WHERE CUSTOMERID = \"" 
                    + idNum + "\" and P_ID = \"" + 1 + "\"");
                    if(!rs.first()) {
                    generated = true;
                    
                    }
                       
                }
                
            } catch (Exception ex) {
                Logger.getLogger(CustomerForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return idNum;
        }
}
