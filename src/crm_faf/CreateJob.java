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


public class CreateJob extends TransitionScene{

        final TextField jobField = new TextField();
        final TextField costField = new TextField();
        final TextField dateField = new TextField();
        final TextField notesField = new TextField();
        final TextField jobsidField = new TextField();
        final TextField customeridField = new TextField();
	
        private WindowTools toolbar;
        private WindowToolbar bar;
        private Data.DbConnection db;
        VBox windowTopBox = new VBox();
    
        ResultSet myResultSet;
        Statement myStatement;
        Stage primaryStage = new Stage();
        
        public void start(Stage primaryStage, DbConnection db) {
		
                this.db = db;
                primaryStage = this.primaryStage;
		primaryStage.setTitle("Job Creation Form");
		BorderPane pane2 = new BorderPane();
                GridPane pane = new GridPane();
                
                pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		Scene scene = new Scene(pane2, 600, 600);
		
                
                toolbar = new WindowTools(db);
                bar = new WindowToolbar(scene, primaryStage, db);
                windowTopBox.getChildren().addAll(bar, toolbar);
                pane2.setTop(windowTopBox);
                
                
		Text sceneTitle = new Text("Enter New Job Information:");
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL,20));
		pane.add(sceneTitle, 0, 0, 2, 1);
                
                pane.add(jobField, 1, 1);
		Label job = new Label("Job name:");
		pane.add(job, 0, 1);
		
		pane.add(costField, 1, 2);
		Label cost = new Label("Job cost:");
		pane.add(cost, 0, 2);
		
		pane.add(dateField, 1, 3);
		Label date = new Label("Date (2015-01-31):");
		pane.add(date, 0, 3);
		
		pane.add(notesField, 1, 4);
		Label notes = new Label("Notes:");
		pane.add(notes, 0, 4);
		
                pane.add(jobsidField, 1, 5);
		Label jobsID = new Label("Job ID:");
		pane.add(jobsID, 0, 5);
                
                //pane.add(customeridField, 1, 6);
		//Label customerID = new Label("Customer ID:");
		//pane.add(customerID, 0, 6);
		
		Button submitButton = new Button("Submit Info");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().add(submitButton);
		pane.add(hbox, 1, 6);
		
		
		final Text submitMessage = new Text();
		pane.add(submitMessage, 1, 10);
		//HBox box = new HBox();
                //box.getChildren().add(pane);
                pane2.setCenter(pane);
                
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			
			
			@Override
			public void handle(ActionEvent event) {
			// TODO : Add if statement to test for each TextField
                            String job = jobField.getText();
                            String cost = costField.getText();
                            String date = dateField.getText();
                            String notes = notesField.getText();
                            String jobsID = jobsidField.getText();
                            String customerID = "2";
                            String P_ID = "1";
                            String completed = "0";
                            String problem = "0";
                            Label errormsg = new Label("Fill out all");
 
                //Check and Validate that all fields are entered.
                if(job.equals("") || cost.equals("") || date.equals("") || notes.equals("") || jobsID.equals("") 
                        || customerID.equals("")) {
                    
                        final Popup popup = new Popup(); 
                        popup.setX(300); popup.setY(200);
                        popup.getContent().add(new HBox(8));
                        HBox hbox = new HBox(8); // spacing = 8
                        hbox.getChildren().add(errormsg);
                        //popup.show(primaryStage);
                        submitMessage.setText("Please fill in all fields.");
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
                                String checkSQL = "select * from jobs"; //where cfName = '"+job+"'clName = '"+cost+"'";
                                       // + "cfName,clName from customer where cfName = '"+job+"'clName = '"+cost+"'";
                                //PreparedStatement ps = connection.preparedStatement(checkSQL);
                                
                                ResultSet rs = myStatement.executeQuery(checkSQL);
                               
                                System.out.println("YOUUUU MADEEE IT!" + job + cost);
                                while(rs.next()) {
                                    System.out.println(rs.getString("P_ID"));
                                    if(rs.getString("jobsID").equals(jobsID)) {
                                        System.out.println("There is already a customer with that ID number");
                                    } else {
                                        System.out.println("Add the new customer");
                                        
                                        myStatement.execute("insert into jobs (P_ID, jobsID, CustomerID, title, cost, date, completed, "
                                                + "problem, notes) "
                                        + "values ('"+P_ID+"','"+jobsID+"','"+customerID+"','"+job+"', '"+cost+"', '"+date+"', '"+completed+"', "
                                                + "'"+problem+"','"+notes+"')");
                                        
                                        System.out.println("Successfully added job!");
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
                    ResultSet rs = myStatement.executeQuery("SELECT 1 FROM jobs WHERE jobsID = \"" 
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
