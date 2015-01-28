/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import org.jfree.data.time.Month;


public class MonthData {
    private Month month;
    private int numCustomers;
    
    public MonthData(Month month, int numCustomers){
        this.month = month;
        this.numCustomers = numCustomers;
    }
    public void setMonth(Month month){
        this.month = month;
    }
    
    public Month getMonth() {
        return month;
    }
    
    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }
    
    public int getNumCustomers() {
        return numCustomers;
    }
}
