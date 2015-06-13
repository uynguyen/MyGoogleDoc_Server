/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThanhTung
 */
public class HandleClientRequestThread implements Runnable{
    Thread t;
    Socket client;

    public HandleClientRequestThread(Socket client){
        this.client = client;
        t  = new Thread(this);        
    }
    @Override
    public void run() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            
            boolean flag = objectInputStream.readBoolean();
            
            if(flag == true){
                LogInThread logInThread = new LogInThread(client, objectOutputStream, objectInputStream);
                logInThread.run();
            } else {
                RegisterThread registerThread = new RegisterThread(client, objectOutputStream, objectInputStream);
                registerThread.run();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(HandleClientRequestThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
