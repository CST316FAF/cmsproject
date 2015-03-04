package crm_faf;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Technician  {

  public void start(Stage primaryStage) {
    primaryStage.setTitle("Technician Info");
    Scene scene = new Scene(new Group(), 450, 250);

    TextField techName = new TextField ();
    TextField techDest = new TextField ();
    TextField techReason = new TextField ();
    
    GridPane grid = new GridPane();
    grid.setVgap(4);
    grid.setHgap(10);
    grid.setPadding(new Insets(5, 5, 5, 5));
    grid.add(new Label("Name: "), 0, 0);
    grid.add(new Label("Destination: "), 0, 1);
    grid.add(new Label("Reason for Call: "), 0, 2);
    grid.add(techName, 1, 0);
    grid.add(techDest, 1, 1);
    grid.add(techReason, 1, 2);
    
    Button backButton = new Button("Back");
    HBox hbox1 = new HBox(10);
    hbox1.setAlignment(Pos.BOTTOM_LEFT);
    hbox1.getChildren().add(backButton);
    grid.add(hbox1, 0, 9);
    
    Button submitButton = new Button("Submit");
    HBox hbox2 = new HBox(10);
    hbox2.setAlignment(Pos.BOTTOM_RIGHT);
    hbox2.getChildren().add(submitButton);
    grid.add(hbox2, 1, 9);

    backButton.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            primaryStage.setScene(new StatusPage().start(primaryStage));
        }

    });
    
    submitButton.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            primaryStage.setScene(new StatusPage().start(primaryStage));
    }

    });
    
    Group root = (Group) scene.getRoot();
    root.getChildren().add(grid);
    primaryStage.setScene(scene);
    primaryStage.show();

  }


}