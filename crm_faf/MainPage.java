package crm_faf;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class MainPage extends Application {

	//still havent tested this yet
        Button btn = new Button("Customer Window UI"); //creates a button named btn with the label Customer Window UI
	HBox hbBtn = new HBox(20); //creates a layout pane named hbBtn with a spacing of 20 pixels
	
        public MainPage() {
        hbBtn.setAlignment(Pos.TOP_RIGHT); //positions the button in the top right of the page
/*	
	btn.setOnAction(new EventHandler<ActionEvent>() { //registers an event handler that sets the actiontarget object below to Customer Window UI button pressed when the user presses the button
	
 
    @Override
    public void handle(ActionEvent e) {
        actiontarget.setText("Customer Window UI button pressed");
    }
	}
*/     
        
        btn.setOnAction ( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                    //actiontarget.setText("Customer Window UI button pressed");
                    System.out.println("Customer Window UI button pressed");
                }
            });

    }
	
    // no clue what you were trying to do here but all the methods have the same 
    // name and adksfj;asdlkfj;lasdfj;asklj needs refactoring
    // copy pasting repeatedly is a no no... especially if it doesn't work
    /*
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Page");
        Button btn2 = new Button();
        btn2.setText("Customer Form");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Click");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn2);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
    

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Page");
        Button btn3 = new Button();
        btn3.setText("Status Scene");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Click");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn3);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Page");
        Button btn4 = new Button();
        btn4.setText("Window Toolbar");
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Click");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn4);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
    */

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
