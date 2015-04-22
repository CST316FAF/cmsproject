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
public class StatusEntry {
    private  SimpleStringProperty ID = new SimpleStringProperty();
    private  SimpleStringProperty Location  = new SimpleStringProperty();
    private  SimpleStringProperty type  = new SimpleStringProperty(); 
    private  SimpleStringProperty appointment  = new SimpleStringProperty();
    
    public StatusEntry(String ID, String Location, String type, 
            String appointment) {
        this.ID = new SimpleStringProperty(ID);
        this.Location = new SimpleStringProperty(Location);
        this.appointment = new SimpleStringProperty(appointment);
        this.type = new SimpleStringProperty(type);
    }


    
    public String getID() {
        return ID.get();
    }
    
    public String getLocation() {
        return Location.get();
    }
        
    public String getAppointment() {
        return appointment.get();
    }

    public String getType() {
        return type.get();
    }
    
    public SimpleStringProperty IDProperty() {
        return ID;
    }
    
    public SimpleStringProperty LocationProperty() {
        return Location;
    }
    
    public SimpleStringProperty AppointmentProperty() {
        return appointment;
    }
    
    void setID(String ID) {
        this.ID.set(ID);
    }

    void setLocation(String Location) {
        this.Location.set(Location);
    }

}
