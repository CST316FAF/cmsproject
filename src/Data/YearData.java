/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import org.jfree.data.time.Month;



public class YearData {
    MonthData[] months = new MonthData[12];
    boolean finished;
    int year;

    YearData(int year) {
        this.year = year;
        for(int x = 1; x < 13; x++) {
            months[x-1] = new MonthData(new Month(x, year));
        }
    }
    
    public MonthData[] getMonths() {
        return months;
    }

    public void setMonths(MonthData[] months) {
        this.months = months;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    
}
