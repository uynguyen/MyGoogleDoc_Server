/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import CustomComponents.StyledTextEditorOnServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tung
 */
public class WorkingServerThread implements Runnable{
    
    Thread t;
    ServerSocket server;
    Notifier notifier;
    String docCode;
    StyledTextEditorOnServer textEditor;
    
    public WorkingServerThread(ServerSocket server, String docCode){
        this.server = server;        
        textEditor = new StyledTextEditorOnServer();
        notifier = new Notifier(docCode, textEditor);
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        do{
            try {
                //accept a port
                Socket client = server.accept();
                
                System.out.println(client.getPort());
                
                //get outputstream
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                oos.flush();                               
                
                //get inputStream
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                
                //create another thread to receive
                System.out.println(notifier.GetNumber());
                ClientReceiveThread clientReceiveThread = new ClientReceiveThread(ois, notifier, textEditor, notifier.GetNumber());
                
                //Register client outputStream
                notifier.Register(oos);
                
            } catch (IOException ex) {
                Logger.getLogger(WorkingServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while (true);
    }
    
}
