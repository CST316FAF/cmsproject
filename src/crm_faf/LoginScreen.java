/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//temporarily using this code as other loginscreen blew up
/*
 * Copyright (c) 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package crm_faf;

import javafx.application.Application;
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
import Data.DbConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginScreen extends Application {
    
    private PasswordField pwbox;
    private Button btn;
    private Stage primaryStage;
    private PasswordField pwBox;
    private Label message;
    private Button createAcct;
    private GridPane grid;
    private Scene scene;
    private DbConnection db = new DbConnection();
    
   
    private TextField userTextField;

    public TextField getUserTextField() {
        return userTextField;
    }

    public void setUserTextField(TextField userTextField) {
        this.userTextField = userTextField;
    }

    public PasswordField getPwbox() {
        return pwbox;
    }

    public void setPwbox(PasswordField pwbox) {
        this.pwbox = pwbox;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public PasswordField getPwBox() {
        return pwBox;
    }

    public void setPwBox(PasswordField pwBox) {
        this.pwBox = pwBox;
    }

    public Label getMessage() {
        return message;
    }

    public void setMessage(Label message) {
        this.message = message;
    }

    public Button getCreateAcct() {
        return createAcct;
    }

    public void setCreateAcct(Button createAcct) {
        this.createAcct = createAcct;
    }

    public GridPane getGrid() {
        return grid;
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    public DbConnection getConnection() {
        return db;
    }

    public void setConnection(DbConnection connection) {
        this.db = connection;
    }


    
    public void setuserTextField(TextField userTextField) {
        this.userTextField = userTextField;
    }
    public TextField getuserTextField() {
        return this.userTextField;
    }
    public void setpwbox(PasswordField pwbox) {
        this.pwbox = pwbox;
    }
    public PasswordField getpwbox() {
        return this.pwbox;
    }
    public void setbtn(Button setbtn) {
        this.btn = setbtn;
    }
    public PasswordField getbtn() {
        return this.pwbox;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("BEST CMS EVER");
        setUp();
        this.Connection();
        this.Buttons();
        scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void setUp() {
        
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("CMS Login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
        createAcct = new Button("Create Account");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn1.getChildren().add(createAcct);
        grid.add(hbBtn1, 1, 5);
        
        message = new Label("");
        message.setAlignment(Pos.BOTTOM_LEFT);
        grid.add(message, 0, 6);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
//        try {
//            connection.connect();
//        } catch (Exception ex) {
//            Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        btn.setDefaultButton(true);
//        btn.setOnAction((ActionEvent e) -> {
//            if(connection.login(userTextField.getText(), pwBox.getText())){
//                primaryStage.setScene(new StatusPage().start(primaryStage, new WindowTools()));
//            }
//            else{
//                message.setText("Invalid Pwd");
//                message.setTextFill(Color.rgb(21, 39, 30));
//                System.out.println("Invalid Username or Password");
//            }
//           // primaryStage.setScene(new StatusPage().start(primaryStage, new WindowTools()));
//        });
//        
//        createAcct.setOnAction((ActionEvent e) -> {
//            
//            primaryStage.setScene(new CreateUser().start(primaryStage));
//        });

        
    }
    public void Connection(){
         try {
            this.db.connect();
        } catch (Exception ex) {
            Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean Buttons(){
        
        btn.setDefaultButton(true);
        btn.setOnAction(login());
        
        createAcct.setOnAction((ActionEvent e) -> {
            
            primaryStage.setScene(new CreateUser().start(primaryStage, db));
        });

        return true;
    }
    private EventHandler<ActionEvent> login() {
        return (ActionEvent event) -> {
            if(db.login(userTextField.getText(), pwBox.getText())){
                primaryStage.setScene(new StatusPage().start(primaryStage, new WindowTools(db), db));
            }
            else{
                message.setText("Invalid Pwd");
                message.setTextFill(Color.rgb(21, 39, 30));
                System.out.println("Invalid Username or Password");
            }
        };
    }
}
