/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

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
    
    public WorkingServerThread(ServerSocket server, String docCode){
        this.server = server;        
        t = new Thread(this);
        notifier = new Notifier(docCode);
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
                
                //Register client outputStream
                notifier.Register(oos);
                
                //get inputStream
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                
                //create another thread to receive
                ClientReceiveThread clientReceiveThread = new ClientReceiveThread(ois, notifier);
                
            } catch (IOException ex) {
                Logger.getLogger(WorkingServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while (true);
    }
    
}
