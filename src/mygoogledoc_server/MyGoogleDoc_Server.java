/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygoogledoc_server;

import Bus.Global;
import Bus.MyBus;
import DAO.DocumentDAO;
import Runnables.HandleClientRequestThread;
import Runnables.SuperServerThread;
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
        
        try {
            //Running SuperServerThread
          // MyBus.getMyInvite(7);
            
          //  MyBus.acceptInvite(7,"1434783082659");
        //    MyBus.leaveDocument(7, "1434783082659");
           // MyBus.deleteDocument("1434783082659");
          //  MyBus.shareDocument("1434783082659", 8, 7);
            
           // MyBus.getListDocument(7);
            
            SuperServerThread superServerThread = new SuperServerThread();
            
            //Host server at port 51399
            ServerSocket server = new ServerSocket(Global.MainServerPort);
              System.out.println(server.getLocalPort());
            Socket client = null;
            do {
                //Accept a client
                client = server.accept();
                
                System.out.println(client.getPort());
                
                HandleClientRequestThread handle = new HandleClientRequestThread(client);

             //   handle.run();


            } while (true);
        } catch (Exception ex) {
            Logger.getLogger(MyGoogleDoc_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
