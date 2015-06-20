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
import java.util.ArrayList;
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
    StyledTextEditorOnServer textEditor;

    public Notifier(String docCode, StyledTextEditorOnServer editor) {
        textEditor = editor;
        document = Bus.MyBus.openDocument(docCode);
        textEditor.setHTMLString(document);
        subcribers = new ArrayList<ObjectOutputStream>();
    }

    public int GetNumber() {
        return subcribers.size() - 1;
    }

    public void Register(ObjectOutputStream os) {
        try {
            subcribers.add(os);
            os.writeUTF(textEditor.getHTMLString());
            os.flush();
        } catch (IOException ex) {
            Logger.getLogger(Notifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    synchronized public void NotifyAll(Object message, int sender) {
        for (int i = 0; i < subcribers.size(); i++) {
            if (i != sender) {
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
}
