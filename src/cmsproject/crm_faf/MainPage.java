package cmsproject.crm_faf;

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


        Button btn1 = new Button("Customer Form"); //creates a button named btn1 with the label Customer Form
	HBox hbBtn1 = new HBox(10); //creates a layout pane named hbBtn1 with a spacing of 10 pixels
	
        Button btn2 = new Button("Login Screen"); //creates a button named btn2 with the label Login Screen
        HBox hbBtn2 = new HBox(20); //creates a layout pane named hbBtn2 with a spacing of 20 pixels
        
        Button btn3 = new Button("Status Page"); //creates a button named btn3 with the label Status Page
        HBox hbBtn3 = new HBox(30); //creates a layout pane named hbBtn3 with a spacing of 30 pixels
        
        Button btn4 = new Button("Status Scene"); //creates a button named btn4 with the label Status Scene
        HBox hbBtn4 = new HBox(40); //creates a layout pane named hbBtn4 with a spacing of 40 pixels
        
        Button btn5 = new Button("User Creation"); //creates a button named btn5 with the label User Creation
        HBox hbBtn5 = new HBox(50); //creates a layout pane named hbBtn5 with a spacing of 50 pixels
        
        public MainPage() {
        hbBtn1.setAlignment(Pos.TOP_RIGHT); //positions the button in the top right of the page   
        
        btn1.setOnAction ( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    System.out.println("Customer Form Button Pressed");
                }
            });

    }
        /*hbBtn2.setAlignment(Pos.TOP_LEFT); //positions the button in the top left of the page   
        
        btn2.setOnAction ( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    System.out.println("Login Screen Button Pressed");
                }
            });

    }
        hbBtn3.setAlignment(Pos.BOTTOM_LEFT); //positions the button in the bottom left of the page   
        
        btn3.setOnAction ( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    System.out.println("Status Page Button Pressed");
                }
            });

    }
        hbBtn4.setAlignment(Pos.BOTTOM_RIGHT); //positions the button in the bottom right of the page   
        
        btn4.setOnAction ( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    System.out.println("Status Scene Button Pressed");
                }
            });

    }
        hbBtn5.setAlignment(Pos.CENTER); //positions the button in the center of the page   
        
        btn5.setOnAction ( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    System.out.println("User Creation Button Pressed");
                }
            });

    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
