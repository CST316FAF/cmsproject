package crm_faf;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class AppointmentNotifications {

   public static void main(String[] args) {

      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.socketFactory.port", "465");
      props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.port", "465");

      Session session = Session.getDefaultInstance(props,
              new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication("abc@gmail.com", "password");
              }
              }
                      
   );
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("abc@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("abc@gmail.com"));
            message.setSubject("Reminder");
            message.setText("Upcoming Appointment");
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Email Sent!");

        } catch(Exception e){
        
        JOptionPane.showMessageDialog(null, e);
        }

      
}
