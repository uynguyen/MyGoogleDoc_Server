/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.Notifier;
import Actions.Action;
import Actions.ActionChat;
import Actions.ActionJoin;
import Actions.ActionQuit;
import CustomComponents.StyledTextEditorOnServer;
import Pojo.Account;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.instrument.Instrumentation;

import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Queue;

/**
 *
 * @author ThanhTung
 */
public class ClientReceiveThread implements Runnable {

    Thread t;
    ObjectInputStream objectInputStream;
    Notifier notifier;

    StyledTextEditorOnServer textEditor;
    String clientUsername;
    Instrumentation instrumentation;


    public ClientReceiveThread(ObjectInputStream is, Notifier notifier, StyledTextEditorOnServer steos, String clientUsername) {
        objectInputStream = is;
        this.notifier = notifier;
        this.textEditor = steos;
        this.clientUsername = clientUsername;

        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        ActionJoin temp = new ActionJoin(null);
        temp.setUsername(clientUsername);
        notifier.NotifyAll(temp, clientUsername);
        while (true) {
            try {

                Actions.Action action = (Actions.Action) objectInputStream.readObject();
                if (action instanceof ActionQuit) {
                    if (((ActionQuit) action).getLeftUser().equalsIgnoreCase(clientUsername)) {
                        notifier.NotifyAll(action, "");
                        break;
                    } else {
                        notifier.NotifyAll(action, clientUsername);
                    }
                } else if (action instanceof ActionChat | action instanceof ActionJoin) {

                    notifier.NotifyAll(action, clientUsername);
                } else {
    

                   // System.out.println("Input");
                    textEditor.ApplyActionChange(action);
                    notifier.NotifyAll(action, clientUsername);
                }

            } catch (IOException | ClassNotFoundException ex) {
                // throw ex;
               // System.out.println("Exception1");
                Logger.getLogger(ClientReceiveThread.class.getName() + t.getName()).log(Level.SEVERE, null, ex);
                break;
            }

        }

        try {
            ObjectOutputStream oos = notifier.GetValue(clientUsername);
            objectInputStream.close();
            oos.flush();
            oos.close();            
            System.out.println(clientUsername + "left");
            notifier.Unregister(clientUsername);
        } catch (IOException ex) {
            Logger.getLogger(ClientReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}