/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class CRM_FAF extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        //temporary Link to status screen
        btn.setText("Go To Status Screen");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            StatusScene s2 = new StatusScene(primaryStage);
            @Override
            public void handle(ActionEvent event) {               
                primaryStage.setScene(s2.status);
                //temporary link to status screen
                
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle("FAF CRM");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
