/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import static sun.net.www.http.HttpClient.New;

public class RSSFeedInput {
    public RSSFeedInput(String url){
        getFeed(url);
    }
    
    public RSSFeedInput(List<String> urls){
        
    } 
    
    public void getFeed(String url) {
        URL address = null; 
        InputStream input = null;
        XMLEventReader reader = null;
        try {
            address = new URL(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(RSSFeedInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            input = address.openStream();
        } catch (IOException ex) {
            Logger.getLogger(RSSFeedInput.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            reader = inputFactory.createXMLEventReader(input);
        } catch (XMLStreamException ex) {
            Logger.getLogger(RSSFeedInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(reader.hasNext()) {
            try {
                XMLEvent event = reader.nextEvent();
            } catch (XMLStreamException ex) {
                Logger.getLogger(RSSFeedInput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
