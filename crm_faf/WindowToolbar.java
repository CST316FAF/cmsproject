/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;


public class WindowToolbar extends MenuBar {
    
    Menu fileMenu = new Menu("File");
    Menu UserMenu = new Menu("User");
    Menu settingsMenu = new Menu("Settings");
    Menu chartMenu = new Menu("Chart Options");
    public WindowToolbar() {
        setUp();
    }


    private void setUp() {
        this.getMenus().add(fileMenu);
        this.getMenus().add(UserMenu);
        this.getMenus().add(chartMenu);
        this.getMenus().add(settingsMenu);
    }
    
    private void setChartMenu() {
        
        
    }    
}