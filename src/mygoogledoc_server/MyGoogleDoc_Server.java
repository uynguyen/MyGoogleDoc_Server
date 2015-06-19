/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygoogledoc_server;

import Bus.MyBus;
import DAO.DocumentDAO;
import Threads.HandleClientRequestThread;
import Threads.SuperServerThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UyNguyen.ITUS
 */
public class MyGoogleDoc_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        
      // MyBus.createNewDocument(4, "TEST");
        
        try {
            //Running SuperServerThread
            
            SuperServerThread superServerThread = new SuperServerThread();
            superServerThread.start();
            
            //Host server at port 51399
            ServerSocket server = new ServerSocket(51399);
              System.out.println("51399");
            Socket client = null;
            do {
                //Accept a client
                client = server.accept();
                
                System.out.println(client.getPort());
                
                HandleClientRequestThread handle = new HandleClientRequestThread(client);
               // handle.run();

            } while (true);
        } catch (IOException ex) {
            Logger.getLogger(MyGoogleDoc_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
