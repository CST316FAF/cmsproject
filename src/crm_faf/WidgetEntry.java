/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Davis
 */
public class WidgetEntry {
    private SimpleStringProperty action;
    private SimpleStringProperty notes;
    private SimpleStringProperty status; 
    private SimpleStringProperty importance;
    private SimpleStringProperty source;
    
    public WidgetEntry(String action, String notes, String status, 
            String importance, String source) {
        this.action = new SimpleStringProperty(action);
        this.notes = new SimpleStringProperty(notes);
        this.importance = new SimpleStringProperty(importance);
        this.status = new SimpleStringProperty(status);
        this.source = new SimpleStringProperty(source);
    }
    
    public SimpleStringProperty getAction() {
        return action;
    }
    
    public SimpleStringProperty getNotes() {
        return action;
    }
        
    public SimpleStringProperty getImportance() {
        return action;
    }

    public SimpleStringProperty getStatus() {
        return action;
    }
}
