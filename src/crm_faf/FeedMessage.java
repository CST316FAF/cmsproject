/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

/**
 *
 * @author Davis
 */
public class FeedMessage {
    private String title;
    private String description;
    private String url;
    private String source;
    private String guid;
    
    
    public FeedMessage(String title, String description, String url, String Source, String guid ) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.source = source;
        this.guid = guid;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getSource() {
        return source;
    }
    
    public String getGuid() {
        return guid;
    }
}
