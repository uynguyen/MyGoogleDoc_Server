/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bus;

import Bus.Global;
import CustomComponents.StyledTextEditorOnServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThanhTung
 */
public class Notifier {

    HashMap<String, ObjectOutputStream> subcribers;
    StyledTextEditorOnServer documentServer = new StyledTextEditorOnServer();
    String document;
    String docCode;
    StyledTextEditorOnServer textEditor;

    public Notifier(String docCode, StyledTextEditorOnServer editor) {
        this.docCode = docCode;
        this.textEditor = editor;
        document = Bus.MyBus.openDocument(docCode);
        textEditor.setHTMLString(document);
        subcribers = new HashMap<>();
    }

    public int GetNumber() {
        return subcribers.size();
    }

    public void Register(String username, ObjectOutputStream os) {
        try {
            subcribers.put(username, os);
            os.writeUTF(textEditor.getHTMLString());
            os.flush();
        } catch (IOException ex) {
            Logger.getLogger(Notifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Unregister(String username) {
        subcribers.remove(username);
        if(subcribers.isEmpty()){
            Global.documentPort.remove(docCode);
        }
    }
    
    public ObjectOutputStream GetValue(String username){
        return subcribers.get(username);
    }

    synchronized public void NotifyAll(Object message, String sender) {
        Set set = subcribers.entrySet();
        set.forEach((Object t) -> {
            Entry<String, ObjectOutputStream> entry = (Entry<String, ObjectOutputStream>) t;
            if (!entry.getKey().equalsIgnoreCase(sender)) {
                try {
                    ObjectOutputStream os = entry.getValue();
                    os.writeObject(message);
                    os.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Notifier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
