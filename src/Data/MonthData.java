/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import org.jfree.data.time.Month;


public class MonthData {
    private Month month;

    private ArrayList<AreaData> areaCodeData = new ArrayList<AreaData>();
    
    public ArrayList<AreaData> getAreaCodeData() {
        return areaCodeData;
    }

    public void setAreaCodeData(ArrayList<AreaData> areaCodeData) {
        this.areaCodeData = areaCodeData;
    }
    
    public MonthData(Month month){
        this.month = month;
        
    }
    public void setMonth(Month month){
        this.month = month;
    }
    
    public Month getMonth() {
        return month;
    }

}
