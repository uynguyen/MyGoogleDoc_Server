/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.Global;
import CustomComponents.StyledTextEditorOnServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThanhTung
 */
public class Notifier {
    
    List<ObjectOutputStream> subcribers;
    StyledTextEditorOnServer documentServer = new StyledTextEditorOnServer();
    String document;
    
    public Notifier(String docCode){        
        document = Bus.MyBus.openDocument(docCode);
        subcribers = new ArrayList<ObjectOutputStream>();
    }
    
    public void Register(ObjectOutputStream os){
        try {
            subcribers.add(os);
            os.writeUTF(document);
            os.flush();
        } catch (IOException ex) {
            Logger.getLogger(Notifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    synchronized public void NotifyAll(Object message){
        for(int i = 0; i < subcribers.size(); i ++){
            try {
                ObjectOutputStream os = subcribers.get(i);
                os.writeObject(message);
                os.flush();
            } catch (IOException ex) {
                Logger.getLogger(Notifier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
