/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygoogledoc_server;

import Threads.LogInThread;
import Threads.RegisterThread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Host server at port 51399
            ServerSocket server = new ServerSocket(51399);
            Socket client = null;
            do {
                //Accept a client
                client = server.accept();
                InputStream inputStream = client.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                //Get the flag (Login or Register)
                String flag = bufferedReader.readLine();

                if (flag.equalsIgnoreCase("login")) {
                    LogInThread logInThread = new LogInThread(client);
                    logInThread.run();
                } else {
                    RegisterThread registerThread = new RegisterThread(client);
                    registerThread.run();
                }

            } while (true);
        } catch (IOException ex) {
            Logger.getLogger(MyGoogleDoc_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
