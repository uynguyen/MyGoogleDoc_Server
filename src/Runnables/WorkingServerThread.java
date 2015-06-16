/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import java.io.IOException;
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
    
    public WorkingServerThread(ServerSocket server){
        this.server = server;        
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        do{
            try {
                Socket client = server.accept();
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(WorkingServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while (true);
    }
    
}
