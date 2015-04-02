/*
package crm_faf;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AppointmentNotifications {

   public static void main(String[] args) {
       
      //Recipient's email ID needs to be mentioned.
      String to = “kunaalgodiwala@gmail.com”; //change accordingly

      //Sender's email ID needs to be mentioned
      String from = “kunaalgodiwala@gmail.com”; //change accordingly
      final String username = “kunaalgodiwala”; //change accordingly
      final String password = “Godiwala123”; //change accordingly

      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "Enter Port Number Here");

      Session session;
       session = Session.getInstance(props,
               new javax.mail.Authenticator() {
                   @Override
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(username, password);
                   }
               });

      try {
         //Create a default MimeMessage object
         Message message = new MimeMessage(session);

         //Set From
         message.setFrom(new InternetAddress(from));

         //Set To
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(to));

         //Set Subject
         message.setSubject(“Reminder of Upcoming Appointment”);
          String date = null;
          String time = null;

         //Set the actual message
         message.setText("Hello, this is a friendly reminder that you have on upcoming appointment on" + date + time);

         //Send message
         Transport.send(message);

         //System.out.println(“Email Sent!“);

      } catch (MessagingException e) {
            throw new RuntimeException(e);
      }
   }

    private static class Upcoming {

        public Upcoming() {
        }
    }

    private static class Reminder {

        public Reminder() {
        }
    }

    private static class Email {

        public Email() {
        }
    }
}
*/