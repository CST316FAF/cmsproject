/*
public void newMessage()
{

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

    //text
    Text productName = new Text(“Notification of Upcoming Appointment”);
    productName.setFont(Font.font(“Times New Roman”, 12));
    grid.add(productName, 0, 2);

    //configure dialog size and background color
    Scene aboutDialogScene = new Scene(grid, 200, 100, Color.WHITE);
    newConnDialog.setScene(aboutDialogScene);
    newConnDialog.show();
}

//separate from above but attempting to see if this way is better
package AppointmentNotification;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    }


    public static void main(String[] args) {
        new JFXPanel(); //initializes the JavaFX thread without having to extend Application
        notifier(“Upcoming Appointment Tomorrow”);
    }

    private static void notifier(String pTitle, String pMessage) {
        Platform.runLater() -> {

	//text
    	Text productName = new Text(“Notification of Upcoming Appointment”);
    	productName.setFont(Font.font(“Times New Roman”, 12));
    	grid.add(productName, 0, 2);
	
    	//configure dialog size and background color
    	Scene aboutDialogScene = new Scene(grid, 200, 100, Color.WHITE);
    	newConnDialog.setScene(aboutDialogScene);
    	newConnDialog.show();
Notifications.create().title(pTitle).text(pMessage).showInformation();

        };
    }
}
*/