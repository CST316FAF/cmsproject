package crm_faf;

import Data.DbConnection;
import java.util.Hashtable;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class TechnicianStatus extends TransitionScene{

    private BorderPane border;
    private StackPane root;
    private DateChooser dateChooser;
    private WindowTools toolbar;
    private WindowToolbar bar;
    private VBox windowTopBox;
    private Scene scene;
    private DbConnection db;
    
    public void start(final Stage primaryStage, WindowTools tBar, DbConnection db) {
        this.db = db;
        primaryStage.setTitle("Technician Status");
        root = new StackPane();
        border = new BorderPane();
        
        windowTopBox = new VBox();
        
        scene = new Scene(border, 300, 250); 
        
        bar = new WindowToolbar(scene, primaryStage, db);
        bar.setToolbar(tBar);
        windowTopBox.getChildren().addAll(bar, tBar);
        border.setTop(windowTopBox);
        border.setCenter(root);
        
        
        dateChooser = new DateChooser();
        root.getChildren().add(dateChooser);
               
        primaryStage.setScene(scene);
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

                    public void handle(WindowEvent event) {
                        System.out.println("date " + dateChooser.getDate());
                    }
                });
        primaryStage.show();
    }
    
   // @Override 
    @Override
    public Hashtable getAttributes(){
        Hashtable table = new Hashtable<String, Object>();
        if(scene != null)
            table.put("scene", scene);
        if(border != null)
            table.put("border", border);
        if(root != null)
            table.put("root", root);
        if(dateChooser != null)
            table.put("dateChooser", dateChooser);
        if(toolbar != null)
            table.put("toolbar", toolbar);
        if(bar != null)
            table.put("bar", bar);
        if(windowTopBox != null)
            table.put("windowTopBox", windowTopBox);
        return table;

    }
    @Override
    public void setAttributes(Hashtable attValues) {
        this.scene = (Scene) attValues.get("scene");
        this.bar = (WindowToolbar) attValues.get("bar");
        this.border = (BorderPane) attValues.get("border");
        this.root = (StackPane) attValues.get("root");
        this.toolbar = (WindowTools) attValues.get("toolbar");
        this.bar = (WindowToolbar) attValues.get("bar");
        this.windowTopBox = (VBox) attValues.get("windowTopBox");
    }
    
       public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public BorderPane getBorder() {
        return border;
    }

    public void setBorder(BorderPane border) {
        this.border = border;
    }

    public StackPane getRoot() {
        return root;
    }

    public void setRoot(StackPane root) {
        this.root = root;
    }

    public DateChooser getDateChooser() {
        return dateChooser;
    }

    public void setDateChooser(DateChooser dateChooser) {
        this.dateChooser = dateChooser;
    }

    public WindowTools getToolbar() {
        return toolbar;
    }

    public void setToolbar(WindowTools toolbar) {
        this.toolbar = toolbar;
    }

    public WindowToolbar getBar() {
        return bar;
    }

    public void setBar(WindowToolbar bar) {
        this.bar = bar;
    }

    public VBox getWindowTopBox() {
        return windowTopBox;
    }

    public void setWindowTopBox(VBox windowTopBox) {
        this.windowTopBox = windowTopBox;
    }

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }

}
