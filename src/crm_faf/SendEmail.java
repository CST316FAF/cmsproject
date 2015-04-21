/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.application.Application;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


/**
 *
 * @author kunaalgodiwala
 */
public class SendEmail extends Application {
    
//    Button sendButton = new Button();
//    
//    public HBox addHBox() {
//        
//        HBox hbox = new HBox();
//        hbox.setPadding(new Insets(15, 12, 15, 12));
//        hbox.setSpacing(10);
//        hbox.setStyle("-fx-background-color: #00FF00;");
//        
//        Button sendButton = this.sendButton;
//        sendButton.setText("Send Email");
//       
//        hbox.getChildren().addAll(sendButton);
//        
//        return hbox;
//    }

    //ArrayList<String> text = new ArrayList<>();   
    
//    public VBox addcenterVBox() {
//        VBox vbox = new VBox();
//        
//        TextField recipients = new TextField();
//        recipients.setPromptText("Enter Email Recipients");
//        
//        TextField subject = new TextField();
//        subject.setPromptText("Enter Subject of Email");
//        
//        TextArea content = new TextArea();
//        content.setPromptText("Enter Content of Email");
//        content.setPrefSize(300, 460);
//        
//        vbox.getChildren().addAll(recipients, subject, content);
//        
//        return vbox;
//    }

    //Stage primaryStage = new Stage();
    
    @Override
    public void start(Stage primaryStage) {
        //primaryStage = this.primaryStage;
        //primaryStage.setTitle("Send Email");
        //BorderPane root = new BorderPane();
        //HBox hbox = addHBox();
        //VBox center = addcenterVBox();
        
        //root.setBottom(hbox);
        //root.setCenter(center);
        
      String to = "failedandfurious@gmail.com";
      String from = "failedandfurious@gmail.com";
      final String username = "failedandfurious@gmail.com";
      final String password = "FailedFurious";
      String subject = "Reminder of Upcoming Appointment";

      Properties properties = System.getProperties();

      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.host", "smtp.asu.edu");
      properties.put("mail.smtp.port", "25");

      properties.setProperty("mail.user", username);
      properties.setProperty("mail.password", password);

      Session session = Session.getInstance(properties,
      
        new javax.mail.Authenticator() {
            @Override
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
              }
        }
                      
        );
        
        //sendButton.setOnAction(new EventHandler<ActionEvent>() {
            
            //@Override
            //public void handle(ActionEvent event) {
                try {
                    
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(from));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(to));
                    message.setSubject(subject);
                    message.setContent("<h:body style=background color:white;font-family:verdana;color:#00FF00;"
                    + "If you are getting this you wrote your first email!<br/><br/>"
                    + "</body>", "text/html; charset=utf-8");
                    Transport.send(message);

                    System.out.println("Message Sent!");
                } catch (MessagingException e) {
                throw new RuntimeException(e);
                }
                //System.out.println("Message Delivered!");

            }    

        //});        
        
        //Scene scene = new Scene(root, 400, 460);
        
        //primaryStage.setScene(scene);
        //primaryStage.show();      
    }
