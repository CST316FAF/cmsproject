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

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Status Notes");

	//add a name for the button
	//Kunaal Godiwala
	//March 18, 2015
        Button saveButton = new Button();
        TextArea notesArea = new TextArea("Enter Status Notes Here");
        StackPane layout = new StackPane();
        layout.getChildren().add(saveButton);
        layout.getChildren().add(notesArea);
        
        
        Scene notesScene = new Scene(layout, 600, 450);
        primaryStage.setScene(notesScene);
        primaryStage.show();
    }
    
}
