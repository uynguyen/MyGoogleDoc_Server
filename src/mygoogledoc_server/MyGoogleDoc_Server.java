/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygoogledoc_server;

import Threads.LogInThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UyNguyen.ITUS
 */
public class MyGoogleDoc_Server {

    private static List<Socket> socketList;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        socketList = new ArrayList<>();
        try {
            ServerSocket server = new ServerSocket(51399);
            Socket client = null;
            do {
                client = server.accept();
                socketList.add(client);
                LogInThread logInThread = new LogInThread(client);
                logInThread.run();
            } while (true);
        } catch (IOException ex) {
            Logger.getLogger(MyGoogleDoc_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
