/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;


public class WindowToolbar extends MenuBar {
    
    Menu fileMenu = new Menu("File");
    Menu UserMenu = new Menu("User");
    Menu settingsMenu = new Menu("Settings");
    Menu chartMenu = new Menu("Chart");
    
    /*
    *I plan to change way graph is used
    */
    Canvas gLine;
    Canvas gPie;
    Canvas gBar;
    
    
    public WindowToolbar() {
        setUp();
    }
    
    public WindowToolbar(Canvas a, Canvas b, Canvas c) {  
        setUp(a,b,c);
    }


    private void setUp() {
        this.getMenus().add(fileMenu);
        this.getMenus().add(UserMenu);
        this.getMenus().add(settingsMenu);
    }
    
    private void setChartMenu() {
        Menu gChoices = new Menu("Graph Type");
        MenuItem DateChoices = new MenuItem("Date");
        MenuItem AreaCode = new MenuItem("Area Code");
        MenuItem Customers = new MenuItem("Customers");
        
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
        
        gChoices.getItems().addAll(lineGraph, barGraph, pieGraph);
        chartMenu.getItems().add(gChoices);
        chartMenu.getItems().add(DateChoices);
        
        
        
    }
    
    private EventHandler<ActionEvent> barSwitch() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                    gBar.setVisible(true);
                    gLine.setVisible(false);
                    gPie.setVisible(false);
                }
            };
    }
    private EventHandler<ActionEvent> lineSwitch() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                    gBar.setVisible(false);
                    gLine.setVisible(true);
                    gPie.setVisible(false);
                }
            };
    }
    private EventHandler<ActionEvent> pieSwitch() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                    gBar.setVisible(false);
                    gLine.setVisible(false);
                    gPie.setVisible(true);
                }
            };
    }

    private void setUp(Canvas a, Canvas b, Canvas c) {
        this.getMenus().add(fileMenu);
        this.getMenus().add(UserMenu);
        this.getMenus().add(chartMenu);
        this.getMenus().add(settingsMenu);
        setChartMenu();
        this.gLine = b;
        this.gPie = c;
        this.gBar = a;
        b.setVisible(false);
        c.setVisible(false);
    }
}