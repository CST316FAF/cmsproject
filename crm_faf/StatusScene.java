/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import DataCharts.Chart;
import Controller.DataCreator;
import Controller.Location;
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class StatusScene {
    
    
    BorderPane pane;
    Scene status;
    Chart graphs;
    
    public StatusScene() {
        setup();
        this.graphs = new Chart();
    }
    
    private void setup() {
        pane = new BorderPane();
        Chart graphs = new Chart();
        ArrayList<Location> locs = new DataCreator().createLocations(); 
        graphs.getCanvas(locs, "pie");
        pane.getChildren().add(graphs.getCanvas());
        graphs.getCanvas();
        status = new Scene(pane, 800, 600);
        WindowToolbar bar = new WindowToolbar();
        pane.setTop(bar);
     //   pane.getChildren().add(bar);
        
    }
}
