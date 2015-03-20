/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WindowTools extends ToolBar {
    private Stage stage;
    private Scene currentScene;
    private HBox buttonBar = new HBox(); 
    private Button widgetButton = new Button();
    private Button nextButton = new Button("Next");
    private Button previousButton = new Button("Previous");
    private ArrayList<Scene> previousScene = new ArrayList<Scene>();
    private ArrayList<Scene> nextScene = new ArrayList<Scene>();
    private StatusWidget widget;
    
    public WindowTools(){
        super();
        setup();
    }

    private void setup() {
        Image statusOk = new Image(getClass().getResourceAsStream("ok-icon.png"));
        widgetButton.setGraphic(new ImageView(statusOk));
        buttonBar.getChildren().addAll(widgetButton,nextButton, previousButton);
        widgetButton.setOnAction(activateWidget());
        nextButton.setOnAction(goToNext());
        previousButton.setOnAction(goToPrevious());
        this.getItems().add(buttonBar);  
    }
    
    private void setWidgetStatusOk() {
        Image statusOk = new Image(getClass().getResourceAsStream("ok-icon.png"));
        widgetButton.setGraphic(new ImageView(statusOk));
    }
    
    private void setWidgetStatusNotOk() {
        Image statusBad = new Image(getClass().getResourceAsStream("Delete-icon.png"));
        widgetButton.setGraphic(new ImageView(statusBad));
    }
    
    
    
    public void setPrevious(Scene last) {
        previousScene.add(last);
    }
    public void setNext(Scene next) {
        nextScene.add(next);
    }

    private EventHandler<ActionEvent> activateWidget() {
        return (ActionEvent event) -> {
            Stage popup = new Stage();
            VBox comp = new VBox();
            StatusWidget widget = new StatusWidget();
        //    widget.setFeed("http://feeds.reuters.com/news/artsculture");
            comp.getChildren().add(widget.getTable());
            Scene popupScene = new Scene(comp, 300, 300);
            popup.setScene(popupScene);
            popup.setX(300);
            popup.setY(400);
        };
    }
    
    private EventHandler<ActionEvent> goToNext() {
        return (ActionEvent event) -> {
            if(nextScene.size() > 0) {
                stage.setScene(nextScene.get(0));
                previousScene.add(currentScene);
                currentScene = nextScene.get(0);
                nextScene.remove(0);
            }
        };
    }
    
    private EventHandler<ActionEvent> goToPrevious() {
        return (ActionEvent event) -> {
            if(previousScene.size() > 0) {
                stage.setScene(previousScene.get(0));
                nextScene.add(currentScene);
                currentScene = nextScene.get(0);
                nextScene.remove(0);
            }
        };
    }
}
