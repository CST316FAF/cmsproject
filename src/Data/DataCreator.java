/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataCreator {

    ArrayList<String> names = new ArrayList<>();
    DbConnection db;
    
    public DataCreator(DbConnection db) {
        this.db = db;
    }

    public ArrayList<AreaData> createLocations(int year, int month){
        ArrayList<AreaData>  locs = new ArrayList<>();
        try {
            Calendar calMin = Calendar.getInstance();
            calMin.set(Calendar.YEAR,year);
            calMin.set(Calendar.MONTH,month);
            calMin.set(Calendar.WEEK_OF_MONTH,1);
            Calendar calMax = Calendar.getInstance();
            calMax.set(Calendar.YEAR,year);
            calMax.set(Calendar.MONTH,month);
            calMax.set(Calendar.WEEK_OF_MONTH,4);
            Date dateMin = calMin.getTime();
            Date dateMax = calMax.getTime();
           
            ResultSet locations = db.selectDataColumn("customer", "*");
           
            while(locations.next()) {
                    ResultSet dateA = db.selectDataColumn("jobs", "date", locations.getString(2), "CustomerID");
                        if(dateA.next() && dateA.getDate(1).after(dateMin) && dateA.getDate(1).before(dateMax)){
                            boolean hasLocation = false;
                            int x = 0;
                            System.out.println(dateA.getString(1));
                            for(int counter = 0; counter < locs.size(); counter++) {
                                if(locs.get(counter).getAreaCode() == dateA.getInt(1)) {
                                    
                                }
                            }
                            if(hasLocation) {
                                System.out.println("fourth barrier");
                                locs.get(x).incrementCustomerNumber();
                                locs.get(x).incrementJobNumber();
                                while(dateA.next()) {
                                    locs.get(x).incrementJobNumber();
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
                        else {
                            
                    }    

            }
        } catch (SQLException ex) {
            Logger.getLogger(DataCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return locs;
    }
    
    private void createMonthData(ArrayList<YearData> years) {
        ResultSet yearCheck = db.selectDataColumn("jobs", "date");

        for(int x = 0; x < years.size(); x++) {
            YearData testYear = years.get(x);

            Calendar beforeYear = Calendar.getInstance();
            beforeYear.set(Calendar.YEAR,testYear.getYear()-1);
            Calendar afterYear = Calendar.getInstance();
            afterYear.set(Calendar.YEAR,testYear.getYear()+1);

            try {
                MonthData[] months = testYear.getMonths();
               //System.out.println("first barrier");
                while(yearCheck.next()) {
                   // System.out.println("second barrier");
                        if( yearCheck.getDate(1).before(afterYear.getTime()) &&
                                yearCheck.getDate(1).after(beforeYear.getTime())){
                            for(x = 0; x < 12; x++) {
                       //             System.out.println("third barrier");
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
        Calendar yearCal = Calendar.getInstance();
        yearCal.set(Calendar.YEAR,year);
        try {
            ResultSet yearCheck = db.selectDataColumn("jobs", "date");
            int offset = 0;
            while(yearCheck.next()) {
                if(yearCheck.getDate(1).after(yearCal.getTime())){
                    YearData nYear = new YearData(2015 + offset);
                    yearCal.set(Calendar.YEAR,year + offset);
                    years.add(nYear);
                    ++offset;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        createMonthData(years);
        return years;
    }
  
}
