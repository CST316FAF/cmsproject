/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import java.util.ArrayList;
import javafx.concurrent.Task;

/**
 *
 * @author Davis
 */
public class UpdateManager {

    private ArrayList<Updateable> UpdateList = new ArrayList<>();
    private boolean dbChanged;
    private boolean feedChanged;
    
   
    public void run() {
    dbChanged = getDbChanged();
    feedChanged = getFeedChanged();
        
       if(dbChanged || feedChanged) {
           for(int x = 0; x < UpdateList.size(); x++) {
               runUpdate(UpdateList.get(x));
           }
       } 
           
           
    }
    private void runUpdate(Updateable a) {
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                a.update();
                this.cancel();
                return null;
            }
        };
        Thread th = new Thread(task);
        th.start();
    }
  

    
    
    private boolean getDbChanged() {
        return false;
    }

    private boolean getFeedChanged() {
        return false;
    }
    
    
    
}
