package application;
	
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


public class StatusPage extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Status Page");
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		Scene scene = new Scene(pane, 800, 600);
		
		Text sceneTitle = new Text("Current Status");
		
		sceneTitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		pane.add(sceneTitle, 0, 0, 2, 1);
		
		
		TableView table = new TableView();
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
		
		final VBox vbox = new VBox();
		vbox.setPrefWidth(700);
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0 , 0, 10));
		vbox.getChildren().addAll(table);
		pane.add(vbox, 0, 1);
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
