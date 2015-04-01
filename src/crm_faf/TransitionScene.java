/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;


import java.util.Hashtable;
import javafx.scene.Scene;




public abstract class TransitionScene  {
    boolean stored;
    Scene scene;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    
    public Hashtable getAttributes(){
        return null;
    }
    public void setAttributes(Hashtable attValues) {
    }
}
