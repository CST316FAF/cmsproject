package Data;


import java.util.ArrayList;
import org.jfree.data.time.Month;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Location {
 
private String name;
private int areaCode;
private ArrayList<String> workers = new ArrayList<String>();
private int customers;
private ArrayList<MonthData> months = new ArrayList<MonthData>();

public Location(int areaCode, ArrayList<String> workers, ArrayList<MonthData> months, int customers) {
    this.name = name;
    this.areaCode = areaCode;
    this.workers = workers;
    this.months = months;
    this.customers = customers;
}

public void setName(String name) {
    this.name = name;
}

public String getName() {
    return name;
}

public void addMonth(MonthData month) {
   this.months.add(month);
}

public void setAreaCode(int areaCode) {
    this.areaCode = areaCode;
}

public int getAreaCode() {
    return areaCode;
}

public void setCustomers(int customers) {
    this.customers = customers;
}

public int getCustomers() {
    return customers;
}

public void setTechnicians(ArrayList<String> workers) {
    this.workers = workers;
}

public void addTechnicians(String worker) {
    this.workers.add(worker);
}

public ArrayList<String> getTechnicians() {
    return workers;
}

public ArrayList<MonthData> getMonths() {
        return months;
    }

public ArrayList<String> getWorkers() {
        return workers;
    }

}

