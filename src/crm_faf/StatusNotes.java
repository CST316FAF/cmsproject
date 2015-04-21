/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;
	
import Data.DataCreator;
import Data.Location;
import DataCharts.Chart;
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
import javafx.scene.control.ScrollPane;
import com.zenjava.jfxflow.actvity.AbstractActivity;
import java.io.File;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import java.sql.Savepoint;
import java.util.prefs.Preferences;
import javafx.event.Event;
import javafx.event.EventHandler;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author ramtin
 */
public class StatusNotes extends Application
{
    
    Button saveButton = new Button();
    Button addNoteButton = new Button();
    Button deleteNoteButton = new Button();
    
    public HBox addHBox() {
        
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        
        Button saveButton = this.saveButton;
        saveButton.setText("Save Note");
        Button addNoteButton = this.addNoteButton;
        addNoteButton.setText("Add New Note");
        Button deleteNoteButton = this.deleteNoteButton;
        deleteNoteButton.setText("Delete Note");
        
        addNoteButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                
            }
        });
        //saveButton.setOnAction(this);
        //addNoteButton.setOnAction(this);
        
        hbox.getChildren().addAll(saveButton, addNoteButton, deleteNoteButton);
        
        return hbox;
        
    }
    
    ListView<String> notesList = new ListView<String>();
    ObservableList<String> notesItems = FXCollections.observableArrayList();
    
    public VBox addLeftVBox() {
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        
        Text title = new Text("Status Notes");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);
        //notesItems = this.notesItems;
        notesItems = FXCollections.observableArrayList(
        "Sample Note");
        notesList.setItems(notesItems);
        notesList.setPrefSize(160, 600);
        try {
            // 1. Get a connection to database
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmsdb", "root", "");
            
            // 2. Create a statement
            Statement myStatement = myConn.createStatement();
            
            // 3. Execute SQL query
            ResultSet myRs = myStatement.executeQuery("select * from statusnotes");
            
            // 4. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("notetitle") + ", " + myRs.getString("notecontent"));
                
                notesItems.add(myRs.getString("notetitle"));
                techNameField.setText(myRs.getString("technician"));
                notesTitleField.setText(myRs.getString("notetitle"));
                notesArea.setText(myRs.getString("notecontent"));
                
            }
        } 
        catch (Exception ecx) {
            
        }
        
        
        vbox.getChildren().add(notesList);
        
        return vbox;
        
    }
 
    
    TextField techNameField = new TextField();
    TextField notesTitleField = new TextField();
    TextArea notesArea = new TextArea();
    
    public VBox addRightVBox() {
        VBox vbox = new VBox();
        
        techNameField.setPromptText("Enter Technician Name here..");
        techNameField.setPrefWidth(20);
        notesTitleField.setPromptText("Enter note title here..");
        notesArea.setPromptText("Enter your note here...");
        notesArea.setPrefSize(300, 460);
        
        vbox.getChildren().addAll(techNameField,notesTitleField, notesArea);
        
        return vbox;
    }
    

    
    
    Stage primaryStage = new Stage();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage = this.primaryStage;
        primaryStage.setTitle("Status Notes");
        BorderPane root = new BorderPane();
        HBox hbox = addHBox();
        VBox leftVbox = addLeftVBox();
        VBox rightVBox = addRightVBox();
        
        root.setBottom(hbox);
        root.setLeft(leftVbox);
        root.setRight(rightVBox);
        
        notesList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("Clicked");
				final int selectedIdx = notesList.getSelectionModel().getSelectedIndex();
		        if (selectedIdx != -1) {
		 
		          final int newSelectedIdx =
		            (selectedIdx == notesList.getItems().size() - 1)
		               ? selectedIdx - 1
		               : selectedIdx;
		 
		          System.out.println(notesList.getSelectionModel().getSelectedItem());
		          
		          try {
			            // 1. Get a connection to database
			            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmsdb", "root", "");
			            
			            // 2. Create a statement
			            Statement myStatement = myConn.createStatement();
			            System.out.println("3");
			            // 3. Execute SQL query
			            ResultSet myRs = myStatement.executeQuery("select * from statusnotes where notetitle='"+notesList.getSelectionModel().getSelectedItem()+"'");
			           while(myRs.next()) {
			            if(myRs.getString("notetitle").equals(notesList.getSelectionModel().getSelectedItem())) {
			            	System.out.println("it worked!");
			            	
			            	techNameField.setText(myRs.getString("technician"));
			            	notesTitleField.setText(myRs.getString("notetitle"));
			            	notesArea.setText(myRs.getString("notecontent"));
			            } else {
			            	System.out.println("nope");
			            }
			            }
			            
			            System.out.println(myRs.getBoolean("notetitle"));
			            
			            
			        } 
			        catch (Exception ecx) {
			            
			        }
		          
		        } else {
		        	System.out.println("Did Select Item ..");
		        }
			}
		});
        
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(notesArea.getText() == "" || notesTitleField.getText() == "" || techNameField.getText() == "") {
                	//TODO: Add a message dialog to notify user that the title field and notes are cannot be left blank
                	System.out.println("Please fill in both title notes text field and the notes area text field.");
                } else {
            	notesItems.add(notesTitleField.getText());
            	try{
            		System.out.println("Connect");
            	// 1. Get a connection to database
                Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmsdb", "root", "");
                System.out.println("2nd");
                // 2. Create a statement
                Statement myStatement = myConn.createStatement();
                System.out.println("3rd");
                // 3. Execute SQL query
                myStatement.execute("insert into statusnotes (notetitle, notecontent, technician) "
                        + "values ('"+notesTitleField.getText()+"', '"+notesArea.getText()+"', '"+techNameField.getText()+"')");
               
                System.out.println("Saved Note!");
            	} catch (Exception e) {
            		
            	}
                }
            }
            
        });
        
        addNoteButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                techNameField.clear();
                notesTitleField.clear();
                notesArea.clear();
                System.out.println("New Note!");
                
            }
            
            
        });
        
        deleteNoteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				final int selectedIdx = notesList.getSelectionModel().getSelectedIndex();
		        if (selectedIdx != -1) {
		 
		          final int newSelectedIdx =
		            (selectedIdx == notesList.getItems().size() - 1)
		               ? selectedIdx - 1
		               : selectedIdx;
		          System.out.println(notesList.getItems().get(selectedIdx));
		          try{
                              // 1. Get a connection to database
                              Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmsdb", "root", "");
		                
		              // 2. Create a statement
		              Statement myStatement = myConn.createStatement();
		                
		              // 3. Execute SQL query
		              myStatement.execute("delete from statusnotes where notetitle='"+notesList.getItems().get(selectedIdx)+"'");
			          } catch(Exception e) {
			        	  
			          }
		          notesList.getItems().remove(selectedIdx);
		          
		          notesList.getSelectionModel().select(newSelectedIdx);
		          
		         
		        } else {
		        	System.out.println("Did Not Remove ..");
		        }
			}
		});
        
        
        Scene scene = new Scene(root,500,560);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    
    public static void main(String[] args) {
    	
        launch(args);
        
    }
    
    public File getNoteFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(StatusNotes.class);
        String filePath = prefs.get("filePath", null);
        if(filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    
    
    
    public void setNoteFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(StatusNotes.class);
        if(file != null) {
            prefs.put("filePath", file.getPath());
            
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");
            
            primaryStage.setTitle("AddressApp");
        }
    }

    
}
