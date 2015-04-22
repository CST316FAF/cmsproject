/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Austin
 */
public class CreateUser extends TransitionScene{
    private TextField newuserTextField;
    private PasswordField pwBox0;
    private PasswordField pwBox1;
    private Button createAcct;
    
    public void setNewUserTextField(TextField newuserTextField) {
        this.newuserTextField = newuserTextField;
    }
    public TextField getNewUserTextField() {
        return this.newuserTextField;
    }
    public void setpwBox0(PasswordField pwBox0){
        this.pwBox0 = pwBox0;
    }
    public PasswordField getpwBox0(){
        return this.pwBox0;
    }
    public void setpwBox1(PasswordField pwBox1){
        this.pwBox1 = pwBox1;
    }
    public PasswordField getpwBox1(){
        return this.pwBox1;
    }
    public void setcreateAcct(Button createAcct){
        this.createAcct = createAcct;
    }
    public Button getcreateAcct(){
        return this.createAcct;
    }
    
    public Scene start(Stage primaryStage) {
    
        primaryStage.setTitle("BEST CMS EVER");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
       
        final Label message = new Label("");
        message.setAlignment(Pos.BOTTOM_LEFT);
        grid.add(message, 1, 5);

        Text scenetitle = new Text("CMS Create Account");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        this.newuserTextField = new TextField();
        grid.add(newuserTextField, 1, 1);

        Label pw0 = new Label("Password:");
        grid.add(pw0, 0, 2);

        this.pwBox0 = new PasswordField();
        grid.add(pwBox0, 1, 2);

        Label pw1 = new Label("Retype Password:");
        grid.add(pw1, 0, 3);

        this.pwBox1 = new PasswordField();
        grid.add(pwBox1, 1, 3);
        
        createAcct = new Button("Create Account");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(createAcct);
        grid.add(hbBtn1, 1, 4);
        
        createAcct.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if((pwBox0.getText().compareTo(pwBox1.getText()) == 0) && pwBox0.getText().length() > 7
                        && newuserTextField.getText().length() > 0){
                    primaryStage.setScene(new StatusPage().start(primaryStage, new WindowTools()));
                }
                else if(pwBox0.getText().length() <= 7) {
                    message.setText("Password must be > 7");
                    message.setTextFill(Color.rgb(21, 39, 30));                   
                }
                else if(newuserTextField.getText().length() <= 0){
                    message.setText("You must enter a username");
                    message.setTextFill(Color.rgb(21, 39, 30));
                }
                else {
                    message.setText("Please verify passwords");
                    message.setTextFill(Color.rgb(21, 39, 30));
                }
                
            }
        });
                
        
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();

        return scene;
        }
    
}
