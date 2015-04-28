/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;
	
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.File;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import java.util.prefs.Preferences;
import javafx.event.EventHandler;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Data.DbConnection;
import javafx.scene.control.Label;

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
        
        hbox.getChildren().addAll(saveButton, addNoteButton, deleteNoteButton);
        
        return hbox;
        
    }
    
    ListView<String> notesList = new ListView<String>();
    ObservableList<String> notesItems = FXCollections.observableArrayList();
    
    public VBox addLeftVBox() {
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        
        Text title = new Text("Current Jobs");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);
        //notesItems = this.notesItems;
        notesItems = FXCollections.observableArrayList(
        "Sample Note");
        notesList.setItems(notesItems);
        notesList.setPrefSize(160, 600);
        
        DbConnection db = new DbConnection();
        try {
            //Get a connection to database
            db.connect(); 
            
            //ResultSet technicianResult = db.selectDataColumn("technician", "techFName", "1");
            ResultSet notesResult = db.selectDataColumn("jobs", "notes", "1");
            ResultSet notesTitleResult = db.selectDataColumn("jobs", "title", "1");
            
            while (notesResult.next() && notesTitleResult.next()) {
                //System.out.println(myRs.getString("notetitle") + ", " + myRs.getString("notecontent"));
                
                notesItems.add(notesTitleResult.getString("title"));
                //techNameField.setText(myRs.getString("technician"));
                notesTitleField.setText(notesTitleResult.getString("title"));
                notesArea.setText(notesResult.getString("notes"));
                
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
        Label notesTitleLabel = new Label();
        notesTitleLabel.setText("Note Title");
        notesTitleLabel.setFont(Font.font("Arial", 14));
        notesTitleLabel.setStyle("-fx-font-weight: bold");
        Label notesAreaLabel = new Label();
        notesAreaLabel.setText("Note");
        notesAreaLabel.setFont(Font.font("Arial", 14));
        notesAreaLabel.setStyle("-fx-font-weight: bold");
        techNameField.setPromptText("Enter Technician Name here..");
        techNameField.setPrefWidth(20);
        notesTitleField.setPromptText("Enter note title here..");
        notesArea.setPromptText("Enter your note here...");
        notesArea.setPrefSize(300, 460);
        
        vbox.getChildren().addAll(techNameField, notesTitleLabel, notesTitleField, notesAreaLabel, notesArea);
        
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
                          
		          DbConnection db = new DbConnection();
		          try {
			            //Get a connection to database
			            db.connect(); 
                                    
                                    ResultSet notesTitleResult = db.selectDataColumn("jobs", "title", "1");
                                    ResultSet notesResult = db.selectDataColumn("jobs", "notes", "1");
			            
			           while(notesTitleResult.next() && notesResult.next()) {
			            if(notesTitleResult.getString("title").equals(notesList.getSelectionModel().getSelectedItem())) {
			            	System.out.println("it worked!");
			            	
			            	//techNameField.setText(myRs.getString("technician"));
			            	notesTitleField.setText(notesTitleResult.getString("title"));
			            	notesArea.setText(notesResult.getString("notes"));
			            } else {
			            	System.out.println("nope");
			            }
			            }
			          
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
