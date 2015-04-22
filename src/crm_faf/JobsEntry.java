
package crm_faf;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Davis
 */
public class JobsEntry {
    private  SimpleStringProperty customer = new SimpleStringProperty();
    private  SimpleStringProperty title  = new SimpleStringProperty();
    private  SimpleStringProperty cost  = new SimpleStringProperty(); 
    private  SimpleStringProperty date  = new SimpleStringProperty();
    private  SimpleStringProperty completed = new SimpleStringProperty();
    private  SimpleStringProperty notes = new SimpleStringProperty();
    
    public JobsEntry(String customer, String title, String cost, 
            String date, String completed) {
        this.customer = new SimpleStringProperty(customer);
        this.title = new SimpleStringProperty(title);
        this.date = new SimpleStringProperty(date);
        this.cost = new SimpleStringProperty(cost);
        this.completed = new SimpleStringProperty(completed);
    }
    public SimpleStringProperty costProperty() {
        return cost;
    }      
    
    public SimpleStringProperty dateProperty() {
        return date;
    }   
    
    public SimpleStringProperty customerProperty() {
        return customer;
    }
    
    public SimpleStringProperty titleProperty() {
        return title;
    }
    
    public SimpleStringProperty completedProperty() {
        return completed;
    }
    
    void setCustomer(String customer) {
        this.customer.set(customer);
    }

    void setTitle(String title) {
        this.title.set(title);
    }

    void setNotes(String notes) {
        this.notes.set(notes);
    }

    void setCompleted(String completed) {
        this.completed.set(completed);
    }
}
