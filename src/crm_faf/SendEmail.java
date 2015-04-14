/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javax.swing.*;

/**
 *
 * @author kunaalgodiwala
 */
public class SendEmail extends Application
{
    
    Button sendButton = new Button();
    
    public HBox addHBox() {
        
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #00FF00;");
        
        Button sendButton = this.sendButton;
        sendButton.setText("Send Email");
        
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
            }
            
        });
        
        hbox.getChildren().addAll(sendButton);
        
        return hbox;
        
    }

    ArrayList<String> text = new ArrayList<>();
    
    
    public VBox addcenterVBox() {
        VBox vbox = new VBox();
        TextField sender = new TextField();
        sender.setPromptText("Enter Email Sender");
        
        TextField recipients = new TextField();
        recipients.setPromptText("Enter Email Recipients");
        
        TextField subject = new TextField();
        subject.setPromptText("Enter Subject of Email");
        
        TextArea content = new TextArea();
        content.setPromptText("Enter Content of Email");
        content.setPrefSize(300, 460);
        
        vbox.getChildren().addAll(sender, recipients, subject, content);
        
        return vbox;
    }
    

    
    
    Stage primaryStage = new Stage();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage = this.primaryStage;
        primaryStage.setTitle("Send Email");
        BorderPane root = new BorderPane();
        HBox hbox = addHBox();
        VBox center = addcenterVBox();
        
        root.setBottom(hbox);
        root.setCenter(center);
        
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                //JOptionPane.showMessageDialog(null, "Email Sent");

                System.out.println("Email Sent!");
            }
            
        });
        
        
        Scene scene = new Scene(root, 400, 460);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}