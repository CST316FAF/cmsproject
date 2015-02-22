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
import javafx.beans.property.SimpleStringProperty;

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
        XMLEvent event = null;
        String action = null;
        String notes = null;
        String source = null;        
        String retAddress;
        
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
                event = reader.nextEvent();
                if (event.isStartElement()) {
                    String part = event.asStartElement().getName().getLocalPart();
                    if(part.compareTo("TITLE") == 0){
                        event = reader.nextEvent();
                        if (event instanceof Characters) {
                          action = event.asCharacters().getData();
                        }
                    }
                    else if(part.compareTo("DESCRIPTION") == 0){
                        event = reader.nextEvent();
                        if (event instanceof Characters) {
                          notes = event.asCharacters().getData();
                        }
                    }
                    else if(part.compareTo("LINK") == 0){
                        event = reader.nextEvent();
                        if (event instanceof Characters) {
                          action = event.asCharacters().getData();
                        }
                    }
                    else if(part.compareTo("GUID") == 0){
                        event = reader.nextEvent();
                        if (event instanceof Characters) {
                          retAddress = event.asCharacters().getData();
                        }
                    }
                    else if(part.compareTo("AUTHOR") == 0){
                        event = reader.nextEvent();
                        if (event instanceof Characters) {
                          source = event.asCharacters().getData();
                        }
                    }
                } 
            } catch (XMLStreamException ex) {
                Logger.getLogger(RSSFeedInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            WidgetEntry entry = new WidgetEntry(action, notes, " ", 
                " ", source);
        }
        
    }
}
