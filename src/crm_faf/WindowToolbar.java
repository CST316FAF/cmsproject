/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import DataCharts.Chart;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import org.jfree.data.time.Month;


public class WindowToolbar extends MenuBar {
    UpdateManager manager = new UpdateManager();
    
    Month startDate;
    Month endDate;
    Chart active;
    
    Menu fileMenu = new Menu("File");
    Menu UserMenu = new Menu("User");
    Menu customerMenu = new Menu("Customer");
    Menu settingsMenu = new Menu("Settings");
    Menu chartMenu = new Menu("Chart");
    Menu technicianMenu = new Menu("Technician");
    /*
    *I plan to change way graph is used
    */
    Canvas gLine;
    Canvas gPie;
    Canvas gBar;
    Scene scene;
    Stage stage;
    Scene last;
    
    public WindowToolbar(Scene scene, Stage stage) {
        setUp(scene, stage);
    }
    
    //adds menu to stage with charts - might change after actual data is used
    public WindowToolbar(Canvas a, Canvas b, Canvas c, Scene scene, Stage stage) {  
        this.scene = scene;
        this.stage = stage;
        setUp(scene, stage);
        setUpCharts(a,b,c);
        manager.run();
    }

    //adds Menu to stage without charts needed
    private void setUp(Scene scene, Stage stage) {
        this.scene = scene;
        this.stage = stage;
//        this.last = new StatusPage().start(stage);
        this.getMenus().add(fileMenu);
        this.getMenus().add(UserMenu);
        this.getMenus().add(settingsMenu);
        this.getMenus().add(customerMenu);
        this.getMenus().add(technicianMenu);
          
    
        //adding add customer scene link
        MenuItem addCust = new MenuItem("Add Customer");
        addCust.setOnAction(goToCustomerAdd());
        MenuItem checkStatus = new MenuItem("Main");
        checkStatus.setOnAction(goToStatus());
        
        //add technician scene link
        
        MenuItem checkTech = new MenuItem("Technician Status");
        checkTech.setOnAction(goToCheckTech());
        technicianMenu.getItems().add(checkTech);
        
        
        this.UserMenu.getItems().add(checkStatus);
        this.customerMenu.getItems().add(addCust);
        
    }
    
    private void setChartMenu() {
        Menu gChoices = new Menu("Graph Type");
        Menu dateChoices = new Menu("Date");
        MenuItem AreaCode = new MenuItem("Area Code");

        //creating graph toggles
        final ToggleGroup graphChoices = new ToggleGroup();
        
        RadioMenuItem lineGraph = new RadioMenuItem("Line");
        lineGraph.setOnAction(lineSwitch());
        lineGraph.setToggleGroup(graphChoices);
        
        RadioMenuItem barGraph = new RadioMenuItem("Bar");
        barGraph.setOnAction(barSwitch());
        barGraph.setToggleGroup(graphChoices);
        
        RadioMenuItem pieGraph = new RadioMenuItem("Pie");
        pieGraph.setOnAction(pieSwitch());
        pieGraph.setToggleGroup(graphChoices);
        
        RadioMenuItem noGraph = new RadioMenuItem("None");
        pieGraph.setOnAction(emptySwitch());
        pieGraph.setToggleGroup(graphChoices);
        
        final ToggleGroup dateToggle = new ToggleGroup();
        
        RadioMenuItem startItem = new RadioMenuItem("Start Date");
        startItem.setOnAction(startSwitch());
        
        RadioMenuItem endItem = new RadioMenuItem("End Date");
        endItem.setOnAction(endSwitch());

        
        gChoices.getItems().addAll(lineGraph, barGraph, pieGraph, noGraph);
        dateChoices.getItems().addAll(startItem, endItem);
        
        chartMenu.getItems().add(gChoices);
        chartMenu.getItems().add(dateChoices);
        
    }
    private EventHandler<ActionEvent> goToCustomerAdd() {
        return (ActionEvent event) -> {
            new CustomerForm().start(stage);
        };
    }
    private EventHandler<ActionEvent> barSwitch() {
        return (ActionEvent event) -> {
            gBar.setVisible(true);
            gLine.setVisible(false);
            gPie.setVisible(false);
        };
    }
    private EventHandler<ActionEvent> lineSwitch() {
        return (ActionEvent event) -> {
            gBar.setVisible(false);
            gLine.setVisible(true);
            gPie.setVisible(false);
        };
    }
    private EventHandler<ActionEvent> pieSwitch() {
        return (ActionEvent event) -> {
            gBar.setVisible(false);
            gLine.setVisible(false);
            gPie.setVisible(true);
        };
    }
    private EventHandler<ActionEvent> emptySwitch() {
        return (ActionEvent event) -> {
            gBar.setVisible(false);
            gLine.setVisible(false);
            gPie.setVisible(false);
        };
    }

    private EventHandler<ActionEvent> startSwitch() {
        return (ActionEvent event) -> {
            System.out.println("selected");
            setStart();
        };
    }
    private EventHandler<ActionEvent> endSwitch() {
        return (ActionEvent event) -> {
            setEnd();
        };
    }
    
    private EventHandler<ActionEvent> goToStatus() {
        return (ActionEvent event) -> {
            new StatusPage().start(stage);
        };
    }
    private EventHandler<ActionEvent> goToCheckTech() {
        return (ActionEvent event) -> {
            new Technician().start(stage);
        };
    }
    
    private void setUpCharts(Canvas a, Canvas b, Canvas c) {
        this.getMenus().add(chartMenu);
        setChartMenu();
        this.gLine = b;
        this.gPie = c;
        this.gBar = a;
        b.setVisible(false);
        c.setVisible(false);
    }
    private void setStart() {
       
        createDateMenu(this.startDate);

    }
    
    private void setEnd() {
      
        createDateMenu(this.endDate);

    }
    
    //not actually making popup window just using another stage for now
    private void createDateMenu(Month m){
        Stage popup = new Stage();
        VBox comp = new VBox();
        Scene popupScene = new Scene(comp, 300, 300);
        popup.setScene(popupScene);
        Button enter = new Button("Ok");

        RadioButton[] months = new RadioButton[12];
        months[0] = new RadioButton("January");
        months[1] = new RadioButton("February");
        months[2] = new RadioButton("March");
        months[3] = new RadioButton("April");
        months[4] = new RadioButton("May");
        months[5] = new RadioButton("June");
        months[6] = new RadioButton("July");
        months[7] = new RadioButton("August");
        months[8] = new RadioButton("September");
        months[9] = new RadioButton("October");
        months[10] = new RadioButton("november");
        months[11] = new RadioButton("December");
        ToggleGroup monthChoices = new ToggleGroup();
        comp.getChildren().addAll(months);
        enter.setOnAction((ActionEvent event) -> {
            for(int x = 0; x < 12; x++) {
                
                if(monthChoices.getSelectedToggle().equals(months[x])) {
                    setMonth(m, x);
                }
            }
        });
        
        for(int x = 0; x < 12; x++) {
            final int y = x;
            months[x].setToggleGroup(monthChoices);
        }
        RadioButton fif = new RadioButton("2015");
        popup.setX(300);
        popup.setY(400);
            
    
    }
     
   private void setMonth(Month m, int x) {
        m = new Month(0, x);
    }   


}