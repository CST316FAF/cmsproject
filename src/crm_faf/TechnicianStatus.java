package crm_faf;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class TechnicianStatus extends Application {

    private BorderPane border;
    private StackPane root;
    private DateChooser dateChooser;
    private WindowTools toolbar;
    private WindowToolbar bar;
    private VBox windowTopBox;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Technician Status");
        root = new StackPane();
        border = new BorderPane();
        
        windowTopBox = new VBox();
        toolbar = new WindowTools();
        Scene scene = new Scene(border, 300, 250); 
        
        bar = new WindowToolbar(scene, primaryStage);
        windowTopBox.getChildren().addAll(bar, toolbar);
        border.setTop(windowTopBox);
        border.setCenter(root);
        
        
        dateChooser = new DateChooser();
        root.getChildren().add(dateChooser);
               
        primaryStage.setScene(scene);
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

                    public void handle(WindowEvent event) {
                        System.out.println("date " + dateChooser.getDate());
                    }
                });
        primaryStage.show();
    }
}
