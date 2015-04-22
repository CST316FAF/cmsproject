/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import Data.DbConnection;
import javafx.stage.Stage;
import javafx.application.Application;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author kunaalgodiwala
 */

public class SendEmail extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      DbConnection db = new DbConnection();
      
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
                try {  
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(from));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(to));
                    message.setSubject(subject);
                    message.setContent("<h1>This is a friendly reminder that you have an appointment coming up!</h1>", "text/html");
                    Transport.send(message);

                    System.out.println("Message Sent!");
                } catch (MessagingException e) {
                throw new RuntimeException(e);
                }
            }        
    }

