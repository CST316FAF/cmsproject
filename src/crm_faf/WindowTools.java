/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import Data.DbConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WindowTools extends ToolBar {
    private Stage stage;
    private TransitionScene currentScene;
    private HBox buttonBar = new HBox(); 
    private Button widgetButton = new Button();
    private Button nextButton =new Button();
    private Button previousButton = new Button();
    private Button emailButton =new Button();
    private Button refreshButton = new Button();
    private DbConnection db;
    private CustomerTable cTable;
    private JobsTable jTable;
    private StatusPage tTable;

  

    public void setWidget(StatusWidget widget) {
        this.widget = widget;
    }
    private StatusWidget widget;
    
    public WindowTools(DbConnection db){
        super();
        this.db = db;
        setup();
    }

    private void setup() {
        widget = new StatusWidget(db);
        widget.checkStatus();
        nextButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Forwards.png"))));
        nextButton.setVisible(false);
        previousButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("Backwards.png"))));
        previousButton.setVisible(false);
        emailButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("email.png"))));
        refreshButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("refresh.png"))));
        
        refreshButton.setOnAction(this.refresh());
        emailButton.setOnAction(sendMail());
        Image statusOk = new Image(getClass().getResourceAsStream("ok-icon.png"));
        widgetButton.setGraphic(new ImageView(statusOk));
        buttonBar.getChildren().addAll(widgetButton, emailButton, refreshButton);
        widgetButton.setOnAction(activateWidget());
        this.getItems().add(buttonBar);  
    }
    public void setTables(CustomerTable ctable, JobsTable jTable, StatusPage tTable) {
        this.cTable = ctable;
        this.jTable = jTable;
        this.tTable = tTable;
    }
    
    private void setWidgetStatusOk() {
        Image statusOk = new Image(getClass().getResourceAsStream("ok-icon.png"));
        widgetButton.setGraphic(new ImageView(statusOk));
    }
    
    private void setWidgetStatusNotOk() {
        Image statusBad = new Image(getClass().getResourceAsStream("Delete-icon.png"));
        widgetButton.setGraphic(new ImageView(statusBad));
    }
    

    private EventHandler<ActionEvent> activateWidget() {
        return (ActionEvent event) -> {
            Stage popup = new Stage();
            VBox comp = new VBox();
            comp.getChildren().add(widget.getTable());
            Scene popupScene = new Scene(comp, 500, 200);
            popup.setScene(popupScene);
            popup.setX(300);
            popup.setY(400);
            popup.toFront();
            popup.centerOnScreen();
            popup.show();
        };
     }
    
    private EventHandler<ActionEvent> sendMail() {
        return (ActionEvent event) -> {
            SendEmail email = new SendEmail();
            email.start(db);
            Stage popup = new Stage();
            VBox comp = new VBox();
            comp.getChildren().add(new Label("Automated Email Sent"));
            Scene popupScene = new Scene(comp, 100, 25);
            popup.setScene(popupScene);
            popup.setX(300);
            popup.setY(400);
            popup.toFront();
            popup.centerOnScreen();
            popup.show();
           
        };
    }
      public Stage getStage() {
            return stage;
        }

        public void setWidgetImage(boolean statBut) {
            if(statBut){
                setWidgetStatusNotOk();
            }
            else {
                setWidgetStatusOk();
                
            }
                
        }
        public void setStage(Stage stage) {
            this.stage = stage;
        }

        public TransitionScene getCurrentScene() {
            return currentScene;
        }

        public void setCurrentScene(TransitionScene currentScene) {
            this.currentScene = currentScene;
        }

        public HBox getButtonBar() {
            return buttonBar;
        }

        public void setButtonBar(HBox buttonBar) {
            this.buttonBar = buttonBar;
        }

        public Button getWidgetButton() {
            return widgetButton;
        }

        public void setWidgetButton(Button widgetButton) {
            this.widgetButton = widgetButton;
        }

        public Button getNextButton() {
            return nextButton;
        }

        public void setNextButton(Button nextButton) {
            this.nextButton = nextButton;
        }

        public Button getPreviousButton() {
            return previousButton;
        }

        public void setPreviousButton(Button previousButton) {
            this.previousButton = previousButton;
        }

        public Button getEmailButton() {
            return emailButton;
        }

        public void setEmailButton(Button emailButton) {
            this.emailButton = emailButton;
        }

        public Button getRefreshButton() {
            return refreshButton;
        }

        public void setRefreshButton(Button refreshButton) {
            this.refreshButton = refreshButton;
        }

        public StatusWidget getWidget() {
            return widget;
        }

        private EventHandler<ActionEvent> refresh() {
            return (ActionEvent event) -> {
                try {
                    tTable.update();
                    jTable.update();
                    cTable.update();
                    setWidgetImage(widget.checkStatus());
                } catch (Exception ex) {
                    Logger.getLogger(WindowTools.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
         }
}
