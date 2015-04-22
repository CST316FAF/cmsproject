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
    private  SimpleStringProperty action = new SimpleStringProperty();
    private  SimpleStringProperty notes  = new SimpleStringProperty();
    private  SimpleStringProperty status  = new SimpleStringProperty(); 
    private  SimpleStringProperty importance  = new SimpleStringProperty();
    private  SimpleStringProperty source = new SimpleStringProperty();
    private  SimpleStringProperty url = new SimpleStringProperty();
    
    public WidgetEntry(String action, String notes, String status, 
            String importance, String source) {
        this.action = new SimpleStringProperty(action);
        this.notes = new SimpleStringProperty(notes);
        this.importance = new SimpleStringProperty(importance);
        this.status = new SimpleStringProperty(status);
        this.source = new SimpleStringProperty(source);
    }

    public WidgetEntry() {
        
    }
    
    public String getAction() {
        return action.get();
    }
    
    public String getNotes() {
        return notes.get();
    }
        
    public String getImportance() {
        return importance.get();
    }

    public String getStatus() {
        return status.get();
    }
    
    @Override
    public String toString() {
        return action.get() + notes.get() + source.get();
    }
    
    public SimpleStringProperty actionProperty() {
        return action;
    }
    
    public SimpleStringProperty notesProperty() {
        return notes;
    }
    
    public SimpleStringProperty Property() {
        return source;
    }
    
    void setAction(String action) {
        this.action.set(action);
    }

    void setNotes(String notes) {
        this.notes.set(notes);
    }

    void setUrl(String retAddress) {
        this.url.set(retAddress);
    }

    void setSource(String source) {
        this.source.set(source);
    }
}
