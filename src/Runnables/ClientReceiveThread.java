/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Actions.Action;
import CustomComponents.StyledTextEditorOnServer;
import Pojo.Account;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThanhTung
 */
public class ClientReceiveThread implements Runnable{
    
    Thread t;
    ObjectInputStream objectInputStream;
    Notifier notifier;
    Account clientInfo;
    Stack<Action> actionStack;
    StyledTextEditorOnServer textEditor;
    int threadNumber;
    
    public ClientReceiveThread(ObjectInputStream is, Notifier notifier, StyledTextEditorOnServer steos, int threadNumber){
        objectInputStream = is;        
        this.notifier = notifier;
        this.textEditor = steos;
        this.threadNumber = threadNumber;
        actionStack = new Stack<>();
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            //receive client information
            clientInfo = (Account)objectInputStream.readObject();
            System.out.println(clientInfo.getID() + ": " + clientInfo.getUsername());
            
            //receive action
            while (true)
            {
                Action action = (Action)objectInputStream.readObject();
                
             //   textEditor.ApplyActionChange(action);
                
                notifier.NotifyAll(action, threadNumber);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
