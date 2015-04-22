
package crm_faf;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Davis
 */
public class CustomerEntry {
    private  SimpleStringProperty name = new SimpleStringProperty();
    private  SimpleStringProperty streetAddress  = new SimpleStringProperty();
    private  SimpleStringProperty city  = new SimpleStringProperty(); 
    private  SimpleStringProperty state  = new SimpleStringProperty();
    private  SimpleStringProperty zip  = new SimpleStringProperty();
    private  SimpleStringProperty phone = new SimpleStringProperty();
    private  SimpleStringProperty email = new SimpleStringProperty();
    
    public CustomerEntry(String name, String streetAddress, String city, String state, String zipCode,
            String phone,  String email) {
        this.zip = new SimpleStringProperty(zipCode);
        this.name = new SimpleStringProperty(name);
        this.streetAddress = new SimpleStringProperty(streetAddress);
        this.state = new SimpleStringProperty(state);
        this.city = new SimpleStringProperty(city);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
    }
    public SimpleStringProperty cityProperty() {
        return city;
    }      
    
    public SimpleStringProperty stateProperty() {
        return state;
    }   
    
    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    public SimpleStringProperty streetAddressProperty() {
        return streetAddress;
    }
    
    public SimpleStringProperty phoneProperty() {
        return phone;
    }
    
    public SimpleStringProperty zipProperty() {
        return zip;
    }
    
    public SimpleStringProperty emailProperty() {
        return email;
    }
    
}