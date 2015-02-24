/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crm_faf;

import crm_faf.WidgetEntry;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
    
    ArrayList<WidgetEntry> widgetList = new ArrayList<WidgetEntry>();
    public RSSFeedInput(String url){
        getFeed(url);
        System.out.println("ran");
    }
    
    public RSSFeedInput(List<String> urls){
        
    } 
    
    public void getFeed(String url) {
        URL address = null; 
        InputStream input = null;
        XMLEventReader reader = null;
        XMLEvent event = null;

        try {
            address = new URL(url);
            System.out.println("ran");
        } catch (MalformedURLException ex) {
            Logger.getLogger(RSSFeedInput.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("f0");
        }
        try {
            input = address.openStream();
            System.out.println("ran");
        } catch (IOException ex) {
            Logger.getLogger(RSSFeedInput.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("f1");
        }
       
       XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            reader = inputFactory.createXMLEventReader(input);
            System.out.println("ran");
        } catch (XMLStreamException ex) {
            Logger.getLogger(RSSFeedInput.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("f2");
        }
        
        while(reader.hasNext()) {
            try {
                String action;
                String notes;
                String source;        
                String retAddress;
                WidgetEntry entry = new WidgetEntry();
                event = reader.nextEvent();
                if (event.isStartElement()) {
                    String part = event.asStartElement().getName().getLocalPart();
                    System.out.println(part);
                    if(part.compareToIgnoreCase("TITLE") == 0){
                        event = reader.nextEvent();
                        if (event instanceof Characters) {
                          action = event.asCharacters().getData();
                          System.out.println(action);
                          entry.setAction(action);
                        }
                    }
                    else if(part.compareToIgnoreCase("DESCRIPTION") == 0){
                        event = reader.nextEvent();
                        if (event instanceof Characters) {
                          notes = event.asCharacters().getData();
                          entry.setNotes(notes);
                          System.out.println(notes);
                        }
                    }
                    else if(part.compareToIgnoreCase("GUID") == 0){
                        event = reader.nextEvent();
                        if (event instanceof Characters) {
                          retAddress = event.asCharacters().getData();
                          System.out.println(retAddress);
                          entry.setUrl(retAddress);
                        }
                    }
                    else if(part.compareToIgnoreCase("AUTHOR") == 0){
                        event = reader.nextEvent();
                        if (event instanceof Characters) {
                          source = event.asCharacters().getData();
                        }
                    }
                } 
            
            widgetList.add(entry);
            
            } catch (XMLStreamException ex) {
                Logger.getLogger(RSSFeedInput.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }
    
    public ArrayList<WidgetEntry> getList(){
        return widgetList;
    }
}
