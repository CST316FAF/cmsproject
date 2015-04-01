/*
package crm_faf;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppointmentNotifications extends Application {
    private Object t;
    
    
    public void newMessage(){

        final Stage newConnDialog = new Stage();
        newConnDialog.initStyle(StageStyle.UNDECORATED);
        newConnDialog.initModality(Modality.WINDOW_MODAL);

        //set position
        newConnDialog.setX(150); //secondStage.setX(primaryStage.getX() + 250);
        newConnDialog.setY(150);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(20, 20, 20, 20));
        
        Text t = new Text (10, 20, "Notification of Upcoming Appointment");

        //configure dialog size and background color
        Scene aboutDialogScene = new Scene(grid, 200, 100, Color.WHITE);
        newConnDialog.setScene(aboutDialogScene);
        newConnDialog.show();
                }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
};


import javafx.application.Application;
import javafx.mail.Message;
import javafx.mail.Send;
import javafx.mail.internet.InternetAddress;


public abstract class AppointmentNotifications extends Application {
    static String from;
    static String to;
    static String subject;
    static String content;


public AppointmentNotifications(String toEmail, String date, String time){

    from = "kunaalgodiwala@yahoo.com";
    to = toEmail;
    subject = "Upcoming Appointment";
    content = "Reminder of Upcoming Appointment\n"
            + "Date of Appointment: " + date + "\n"
            + "Time of Appointment: " + time;        
}

    public static void send() {

    Message msg;
    msg = new Message();
    msg.setFrom(new InternetAddress(from));
    msg.setRecipient(new InternetAddress(to));
    msg.setSubject(subject);
    msg.setText(content);

    
    // Send the message
    Send.send(msg);
};

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppointmentNotifications extends Application {

    @Override
    public void start(Stage primaryStage) {
        final HBox root = new HBox(5);
        final TextField SendTo = new TextField("help@example.com");
        final TextField From = new TextField("kunaalgodiwala@yahoo.com");
        final TextField Subject = new TextField("Reminder of Upcoming Appointment");
        final TextField Content = new TextField("Upcoming Appointment");
        final Button ClickToSend = new Button("Send");

        EventHandler<ActionEvent> goHandler = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                getHostServices().showDocument("mailto:"+SendTo.getText());
                getHostServices().showDocument("mailto:"+From.getText());
                getHostServices().showDocument("mailto:"+Subject.getText());
                getHostServices().showDocument("mailto:"+Content.getText());
            }

        };

        SendTo.setOnAction(goHandler);
        ClickToSend.setOnAction(goHandler);

        root.getChildren().addAll(SendTo, ClickToSend, From, Subject, Content);
        final Scene scene = new Scene(root, 250, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/
