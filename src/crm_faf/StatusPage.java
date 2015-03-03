package crm_faf;
	
import Data.DataCreator;
import Data.Location;
import DataCharts.Chart;
import java.util.ArrayList;
import javafx.application.Application;
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
import com.zenjava.jfxflow.navigation.NavigationManager;
import com.zenjava.jfxflow.navigation.Place;


public class StatusPage extends AbstractActivity {

	public Scene start(Stage primaryStage) {
		
		primaryStage.setTitle("Status Page");
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
                Chart lineGraph = new Chart();
                Chart barGraph = new Chart();
                Chart pieGraph = new Chart();

                ArrayList<Location> locs = new DataCreator().createLocations(); 

                lineGraph.getCanvas(locs, "bar");
                barGraph.getCanvas(locs, "pie");
                pieGraph.getCanvas(locs, "line");

                BorderPane pane2 = new BorderPane();
		Scene scene = new Scene(pane2, 800, 600);
		WindowToolbar bar = new WindowToolbar(lineGraph.getCanvas(), 
                        barGraph.getCanvas(), pieGraph.getCanvas(),
                        scene, primaryStage);
                
                ScrollPane scroll = new ScrollPane();
                scroll.setPrefHeight(40);
                scroll.setPrefWidth(20);
                pane2.setRight(scroll);
               
                pane2.setCenter(pane);
                pane2.setTop(bar);
                
		Text sceneTitle = new Text("Current Status");
		
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		pane.add(sceneTitle, 0, 0, 2, 1);
		
		
		TableView table = new TableView();
		table.setPrefHeight(500);
                table.setEditable(true);
		TableColumn employeeIDColumn = new TableColumn("Employee ID");
		employeeIDColumn.setPrefWidth(100);
		TableColumn locationColumn = new TableColumn("Current Location");
		locationColumn.setPrefWidth(200);
		TableColumn typeOfWorkColumn = new TableColumn("Work being Performed");
		typeOfWorkColumn.setPrefWidth(150);
		TableColumn nextAppointmentColumn = new TableColumn("Next Appointment Time");
		nextAppointmentColumn.setPrefWidth(200);
		table.getColumns().addAll(employeeIDColumn, locationColumn, typeOfWorkColumn, nextAppointmentColumn);
		

                StatusWidget widget = new StatusWidget();
                //temporary feed to test widget properties
                widget.setFeed("http://feeds.reuters.com/news/artsculture");
		
                final VBox vbox2 = new VBox();
                vbox2.setSpacing(5);
                vbox2.setPadding(new Insets(10, 0, 0, 10));
                vbox2.getChildren().addAll(widget.getTable());
                vbox2.setPrefHeight(300);
                vbox2.setMinSize(200, 200);
                
                final VBox vbox = new VBox();
		vbox.setPrefWidth(700);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0 , 0, 10));
		vbox.getChildren().addAll(vbox2, table,lineGraph.getCanvas(),barGraph.getCanvas(),pieGraph.getCanvas());
		pane.add(vbox, 0, 1);
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
		return scene;
	}
	
}
