package crm_faf;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.management.Notification;

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
        
        //t.setText("This is a text sample");
        //t.setFont(Font.font ("Verdana", 20));
        
        //Text t = new Text(“Notification of Upcoming Appointment”);
        //t.setFont(Font.font(“Times New Roman”, 12));
        //grid.add(t, 0, 2);

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