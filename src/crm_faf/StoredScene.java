/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import java.util.Hashtable;

/**
 *
 * @author Davis
 */
public class StoredScene {
    private String sceneType;
    private Hashtable attributes = new Hashtable();

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    public Hashtable getAttributes() {
        return attributes;
    }

    public void setAttributes(Hashtable attributes) {
        this.attributes = attributes;
    }
    
}

