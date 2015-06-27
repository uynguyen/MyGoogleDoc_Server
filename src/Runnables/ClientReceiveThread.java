/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Actions.Action;
import Actions.ActionChat;
import Actions.ActionJoin;
import CustomComponents.StyledTextEditorOnServer;
import Pojo.Account;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.instrument.Instrumentation;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThanhTung
 */
public class ClientReceiveThread implements Runnable {

    Thread t;
    ObjectInputStream objectInputStream;
    Notifier notifier;
    Account clientInfo;
    Stack<Action> actionStack;
    StyledTextEditorOnServer textEditor;
    int threadNumber;
    Instrumentation instrumentation;

    public ClientReceiveThread(ObjectInputStream is, Notifier notifier, StyledTextEditorOnServer steos, int threadNumber) {
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
            clientInfo = (Account) objectInputStream.readObject();
            System.out.println(clientInfo.getID() + ": " + clientInfo.getUsername());

            ActionJoin temp = new ActionJoin(null);
            temp.setUsername(clientInfo.getUsername());
            notifier.NotifyAll(temp, threadNumber);

            //receive action
            while (true) {
                try {

                  
                        Actions.Action action = (Actions.Action) objectInputStream.readObject();
                        System.out.println("Input");

                        if (action instanceof ActionChat) {

                            notifier.NotifyAll(action, threadNumber);
                        } else {
                             textEditor.ApplyActionChange(action);
                            notifier.NotifyAll(action, threadNumber);
                        }
                    

                } catch (IOException | ClassNotFoundException ex) {
                    // throw ex;
                    System.out.println("Exception1");
                    Logger.getLogger(ClientReceiveThread.class.getName() + t.getName()).log(Level.SEVERE, null, ex);

                }

            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Exception2");
            Logger.getLogger(ClientReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
