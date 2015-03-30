package application;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
 



public class TableViewSample extends Application {
 
    private TableView<Person> table = new TableView<Person>();
   
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Inventory List");
        stage.setWidth(700);
        stage.setHeight(600);
 
        final Label label = new Label("Inventory List");
        label.setFont(new Font("Arial", 20));
        
        final TextField addName = new TextField();
        addName.setPromptText("Name");
       
        final Button addButton = new Button("Add New Technician");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            
            }
        });
        
     
 
 
    
        
      
 
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>("name"));
    
        nameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> t) {
                 
                }
             }
        );
 
 
 
      
        table.getColumns().addAll(nameCol);
 
        final TextField addTool = new TextField();
        addTool.setPromptText("Tool Name");
        addTool.setMaxWidth(nameCol.getPrefWidth());

 
        final Button addToolButton = new Button("Add Tool");
        addToolButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
              
            	
            	addNewTool(addTool.getText());
                addTool.clear();
            }
        });
        
        final Button deleteToolButton = new Button("Delete Selected Tool");
        deleteToolButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

            }
        });
 
      
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
       
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
    
    
    public void addNewTool(String toolName) {
    	
        TableColumn newCol = new TableColumn(toolName);
        newCol.setMinWidth(100);
        newCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>(toolName));
      
        table.getColumns().addAll(newCol);
        
    
    }
 
   public static class Person {
 
      
        
 
    }
 
   class EditingCell extends TableCell<Person, String> {
 
        private TextField textField;
 
     
 
 
      
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                  
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                
                    setGraphic(null);
                }
            }
        }
 
        private void createTextField() {
         
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, 
                    Boolean arg1, Boolean arg2) {
                        if (!arg2) {
                            commitEdit(textField.getText());
                        }
                }
            });
        }
        
        
        
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
        
    }
}