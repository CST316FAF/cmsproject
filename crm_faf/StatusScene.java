/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import DataCharts.Chart;
import Data.DataCreator;
import Data.Location;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class StatusScene {
    
    
    BorderPane pane;
    Scene status;
    Chart graphs;
    Stage stage;
    
    public StatusScene(Stage stage) {     
        this.stage = stage;
        setup();
        this.graphs = new Chart();       
    }
    
    private void setup() {
        pane = new BorderPane();
        Chart lineGraph = new Chart();
        Chart barGraph = new Chart();
        Chart pieGraph = new Chart();
        
        ArrayList<Location> locs = new DataCreator().createLocations(); 
        
        lineGraph.getCanvas(locs, "bar");
        barGraph.getCanvas(locs, "pie");
        pieGraph.getCanvas(locs, "line");
        
        pane.getChildren().add(lineGraph.getCanvas());
        pane.getChildren().add(barGraph.getCanvas());
        pane.getChildren().add(pieGraph.getCanvas());
  
        status = new Scene(pane, 800, 600);
        WindowToolbar bar = new WindowToolbar(lineGraph.getCanvas(), pieGraph.getCanvas(), barGraph.getCanvas(), status, stage);
        pane.setTop(bar);
     //   pane.getChildren().add(bar);
        
    }
}
