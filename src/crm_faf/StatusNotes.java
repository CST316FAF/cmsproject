/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;
	
import Data.DataCreator;
import Data.Location;
import DataCharts.Chart;
import java.util.ArrayList;
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
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author ramtin
 */
public class StatusNotes extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public HBox addHBox() {
        
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        //hbox.setStyle("-fx-background-color: #336699");
        
        Button saveButton = new Button("Save");
        saveButton.setPrefSize(100, 20);
        
        Button addNewNoteButton = new Button("Add New Note");
        addNewNoteButton.setPrefSize(100, 20);
        hbox.getChildren().addAll(saveButton, addNewNoteButton);
        
	//told by Ramtin that when this code is run, it crashes
	//I’m not able to figure out why or am able to find an issue
	//one suggestion that I have is to add a Edit Note button and
	//a Delete Note button.
        
        return hbox;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Status Notes");
        TextArea notesArea = new TextArea("Enter Status Notes Here");
        
        notesArea.setPrefColumnCount(120);
        notesArea.setPrefRowCount(240);
        
        BorderPane layout = new BorderPane();
        layout.getChildren().add(notesArea);
        
        HBox hbox = addHBox();
        
        layout.setBottom(hbox);
        layout.setTop(notesArea);
        
        Scene notesScene = new Scene(layout, 600, 450);
        primaryStage.setScene(notesScene);
        primaryStage.show();
    }
    
}
