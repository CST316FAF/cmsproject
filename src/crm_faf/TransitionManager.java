/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Davis
 */
public class TransitionManager {
    ArrayList<Class> classList = new ArrayList<>();
    ArrayList<StoredScene> previousSceneList = new ArrayList<>();
    ArrayList<StoredScene> nextSceneList = new ArrayList<>();

    public ArrayList<StoredScene> getPreviousSceneList() {
        return previousSceneList;
    }

    public void setPreviousSceneList(ArrayList<StoredScene> previousSceneList) {
        this.previousSceneList = previousSceneList;
    }

    public ArrayList<StoredScene> getNextSceneList() {
        return nextSceneList;
    }

    public void setNextSceneList(ArrayList<StoredScene> nextSceneList) {
        this.nextSceneList = nextSceneList;
    }
    
    
    public TransitionManager() {
        loadLists();
    }

    private void loadLists() {
        classList.add(StatusPage.class);
        classList.add(Technician.class);
        classList.add(TechnicianStatus.class);
        classList.add(CustomerForm.class);
        classList.add(CreateUser.class);
    //    classList.add(Inventory3.class);
    }   
    public Scene getNextSceneClass() {
        Scene n = null;  
        for(int x = 0; x < classList.size(); x++){
            if(nextSceneList.get(0).getSceneType().equals(classList.get(x).getName()))
                try {
                    n = ((TransitionScene)classList.get(0).newInstance()).getScene();
            } catch (InstantiationException ex) {
                Logger.getLogger(TransitionManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(TransitionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return n;
    }
    public TransitionScene getCurrentTransitionScene() {
            TransitionScene n = null;
            try {
                n = ((TransitionScene)classList.get(0).newInstance());
            } catch (InstantiationException ex) {
                Logger.getLogger(TransitionManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(TransitionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return n;
    }
    public void incrementNextSceneList() {
        nextSceneList.remove(0);
    }
    public void incrementPreviousSceneList() {
        previousSceneList.remove(0);
    }
    
    public void addNextScene(TransitionScene scene) {
        StoredScene temp = new StoredScene();
        temp.setAttributes(scene.getAttributes());
        for(int x = 0; x < classList.size(); x++) {
            if(scene.getClass().isInstance(classList.get(x)))
                temp.setSceneType(classList.get(x).getName());
        }
        nextSceneList.add(temp);
    }
    public void addPreviousScene(TransitionScene scene) {
        StoredScene temp = new StoredScene();
        temp.setAttributes(scene.getAttributes());
        for(int x = 0; x < classList.size(); x++) {
            if(scene.getClass().isInstance(classList.get(x)))
                temp.setSceneType(classList.get(x).getName());
        }
        previousSceneList.add(temp);
    }

    Scene getPreviousSceneClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}