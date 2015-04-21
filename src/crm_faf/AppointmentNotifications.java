package crm_faf;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.swing.JOptionPane;
import javafx.application.Application;


public abstract class AppointmentNotifications extends Application {

    public static void emailNotify() {

      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      Session session = Session.getDefaultInstance(props,
              new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication("failedandfurious@gmail.com", "FailedFurious");
              }
        }
                      
        );
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("failedandfurious@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("kunaalgodiwala@yahoo.com"));
            message.setSubject("Reminder");
            message.setText("Upcoming Appointment");
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Email Sent!");

        } catch(Exception e){
        
        JOptionPane.showMessageDialog(null, e);
        }
    }
}
