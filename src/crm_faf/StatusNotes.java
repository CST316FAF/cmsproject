///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package crm_faf;
//	
//import Data.DataCreator;
//import Data.Location;
//import DataCharts.Chart;
//import java.util.ArrayList;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.stage.Stage;
//import javafx.scene.Scene;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.scene.control.ScrollPane;
//import com.zenjava.jfxflow.actvity.AbstractActivity;
//import javafx.application.Application;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.event.ActionEvent;
//import java.sql.Savepoint;
//
//import javafx.event.Event;
//import javafx.event.EventHandler;
//
//import javafx.scene.control.Hyperlink;
//
//
///**
// *
// * @author ramtin
// */
//public class StatusNotes extends Application
//{
//    
//    Button saveButton = new Button();
//    Button addNoteButton = new Button();
//    
//    public HBox addHBox() {
//        
//        HBox hbox = new HBox();
//        hbox.setPadding(new Insets(15, 12, 15, 12));
//        hbox.setSpacing(10);
//        hbox.setStyle("-fx-background-color: #336699;");
//        
//        Button saveButton = this.saveButton;
//        saveButton.setText("Save Note");
//        Button addNoteButton = this.addNoteButton;
//        addNoteButton.setText("Add New Note");
//        
//        //saveButton.setOnAction(this);
//        //addNoteButton.setOnAction(this);
//        
//        hbox.getChildren().addAll(saveButton, addNoteButton);
//        
//        return hbox;
//        
//    }
//    
//    Hyperlink options[] = new Hyperlink[0];
//    
//    public VBox addVBox() {
//        
//        VBox vbox = new VBox();
//        vbox.setPadding(new Insets(10));
//        vbox.setSpacing(10);
//        
//        Text title = new Text("Status Notes");
//        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
//        vbox.getChildren().add(title);
//        
//        Hyperlink options[] = new Hyperlink[] {
//            new Hyperlink("New Note")
//        };
//        int listLength = options.length;
//        for (int i = 0; i < listLength; i++) {
//            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
//            vbox.getChildren().add(options[i]);
//        }
//        
//        
//        return vbox;
//        
//    }
//    
//    
//    
//    public TextArea addNotesArea() {
//        
//        TextArea notesArea = new TextArea();
//        notesArea.setPromptText("Enter your note here...");
//        notesArea.setPrefSize(300, 460);
//        
//        return notesArea;
//    }
//    
//    Stage primaryStage = new Stage();
//    
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage = this.primaryStage;
//        primaryStage.setTitle("Status Notes");
//        BorderPane root = new BorderPane();
//        HBox hbox = addHBox();
//        VBox vbox = addVBox();
//        TextArea notesArea = addNotesArea();
//        
//        root.setBottom(hbox);
//        root.setLeft(vbox);
//        root.setRight(notesArea);
//        
//        saveButton.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                
//                
//                System.out.println("Save!");
//            }
//            
//        });
//        
//        
//        Scene scene = new Scene(root,400,460);
//        
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        
//    }
//    
//    
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//    public File getNoteFilePath() {
//        Preferences prefs = Preferences.userNodeForPackage(StatusNotes.class);
//        String filePath = prefs.get("filePath", null);
//        if(filePath != null) {
//            return new File(filePath);
//        } else {
//            return null;
//        }
//    }
//    
//    
//    
//    public void setNoteFilePath(File file) {
//        Preferences prefs = Preferences.userNodeForPackage(StatusNotes.class);
//        if(file != null) {
//            prefs.put("filePath", file.getPath());
//            
//            primaryStage.setTitle("AddressApp - " + file.getName());
//        } else {
//            prefs.remove("filePath");
//            
//            primaryStage.setTitle("AddressApp");
//        }
//    }
//
//    
//}