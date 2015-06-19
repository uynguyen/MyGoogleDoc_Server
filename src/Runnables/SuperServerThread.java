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
public class SuperServerThread implements Runnable{
    
    Thread t;
    
    public SuperServerThread(){
        t = new Thread(this);
        t.start();
    }
    
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(13599);
            System.out.println("13599");
            Socket client = null;
            do{
                client = server.accept();
                
                System.out.println(client.getPort());
                
                HandleLoggedInClientRequestThread handler = new HandleLoggedInClientRequestThread(client);                
                
            } while (true);
        } catch (IOException ex) {
            Logger.getLogger(SuperServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
