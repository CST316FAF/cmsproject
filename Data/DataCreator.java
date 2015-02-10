/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Data.Location;
import Data.MonthData;
import java.util.ArrayList;
import org.jfree.data.time.Month;

public class DataCreator {

    ArrayList<String> names = new ArrayList<String>();
    
    private void populateNames() {
        names.add("John");
        names.add("Bruce");
        names.add("Robert");
        names.add("David");
        names.add("Michael");
        names.add("Jennifer");
        names.add("Lauren");
        names.add("Amanda");
        names.add("Anthony");
        names.add("Charles");
        names.add("Jeff");
        names.add("Pedro");     
    }
    
    public ArrayList<Location> createLocations(){
        ArrayList<Location> locs = new ArrayList<Location>();
        populateNames();
        for(int x = 0; (x + 2) < names.size(); x++) {
            ArrayList<String> locNames = new ArrayList<String>();
            locNames.add(names.get(x));
            locNames.add(names.get(x+1));
            Location loc = new Location((int)(Math.random() * 1234), 
                    locNames, createMonthData(), (int)(Math.random() * 420));
            locs.add(loc);
        }
        return locs;
    }
    private int randomInt() {
        return (int)(Math.random() * 1234);
    }
    
    public ArrayList<MonthData> createMonthData() {
        ArrayList<MonthData> months = new ArrayList<MonthData>();
        int year = 2014;
        for(int x = 0; x < 12; x++) {
            if(x > 11) {
                year++;
            }
            months.add(new MonthData(new Month(x + 1, year), (int)(Math.random() * 42)));
        }
        return months;
    }
    
  
}
