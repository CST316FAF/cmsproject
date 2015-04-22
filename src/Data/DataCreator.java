/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


import Data.MonthData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.time.Month;

public class DataCreator {

    ArrayList<String> names = new ArrayList<String>();
    DbConnection db = new DbConnection();

    public ArrayList<AreaData> createLocations(int year, int month){
        ArrayList<AreaData>  locs = new ArrayList<AreaData>();
        try {
            Calendar calMin = Calendar.getInstance();
            calMin.set(Calendar.YEAR,year);
            calMin.set(Calendar.MONTH,month);
            calMin.set(Calendar.WEEK_OF_MONTH,1);
            Calendar calMax = Calendar.getInstance();
            calMin.set(Calendar.YEAR,year);
            calMin.set(Calendar.MONTH,month);
            calMin.set(Calendar.WEEK_OF_MONTH,4);
            Date dateMin = calMin.getTime();
            Date dateMax = calMax.getTime();
           
            ResultSet locations = db.selectDataColumn("customers", "*", "1");
           
            while(locations.next()) {
                    ResultSet dateA = db.selectDataColumn("jobs", "date",  "1", locations.getString(2), "CustomerID");
                    for(int  x = 0; x < locs.size(); x++) {
                        if(locs.get(x).getAreaCode() == locations.getInt(7)){
                            if(dateA.next() && dateA.getDate(1).after(dateMin) && dateA.getDate(1).before(dateMax)){

                                locs.get(x).incrementCustomerNumber();
                                locs.get(x).incrementJobNumber();
                                while(dateA.next()) {
                                    locs.get(x).incrementJobNumber();
                                }
                            }
                        }
                        else {
                        AreaData data = new AreaData();
                        locs.add(data);
                        data.setAreaCode(locations.getInt(7));
                        if(dateA.next() && dateA.getDate(1).after(dateMin) && dateA.getDate(1).before(dateMax)){
                                data.incrementCustomerNumber();
                                data.incrementJobNumber();
                            while(dateA.next()) {
                                data.incrementJobNumber();
                                }
                            }
                    }
                    }    

            }
        } catch (SQLException ex) {
            Logger.getLogger(DataCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return locs;
    }
    
    private void createMonthData(ArrayList<YearData> years) {
        ResultSet yearCheck = db.selectDataColumn("jobs", "date", "1");

        for(int x = 0; x < years.size(); x++) {
            YearData testYear = years.get(x);

            Calendar beforeYear = Calendar.getInstance();
            beforeYear.set(Calendar.YEAR,testYear.getYear()-1);
            Calendar afterYear = Calendar.getInstance();
            afterYear.set(Calendar.YEAR,testYear.getYear()-1);

            try {
                MonthData[] months = testYear.getMonths();
                while(yearCheck.next()) {
                        if( yearCheck.getDate(1).before(afterYear.getTime()) &&
                                yearCheck.getDate(1).after(beforeYear.getTime())){
                            for(x = 0; x < 12; x++) {
                                    
                                    months[x].setAreaCodeData(createLocations(testYear.getYear(), months[x].getMonth().getMonth()));
                            } 
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<YearData> generateYearData() throws Exception {
        
        int year = 2015;
        ArrayList<YearData> years = new ArrayList<YearData>();
        YearData start = new YearData(2014);
        years.add(start);
        Calendar yearCal = Calendar.getInstance();
        yearCal.set(Calendar.YEAR,year);
        try {
            db.connect();
            ResultSet yearCheck = db.selectDataColumn("jobs", "date", "1");
            while(yearCheck.next()) {
                    if(yearCheck.getDate(1).after(yearCal.getTime())){
                    YearData nYear = new YearData(2015);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return years;
    }
  
}
