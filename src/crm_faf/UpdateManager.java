/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import java.util.ArrayList;

/**
 *
 * @author Davis
 */
public class UpdateManager implements Runnable{

    private ArrayList<Object> UpdateList = new ArrayList<>();
    private boolean dbChanged;
    private boolean feedChanged;
    
    @Override
    public void run() {
    dbChanged = getDbChanged();
    feedChanged = getFeedChanged();
        
       if(dbChanged || feedChanged) {
           for(int x = 0; x < UpdateList.size(); x++) {
               
           }
       } 
           
           
    }

    private boolean getDbChanged() {
        return false;
    }

    private boolean getFeedChanged() {
        return false;
    }
    
    
    
}
