/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Bus.Global;
import Bus.MyBus;
import CommunicatePackage.CreateDocPackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tung
 */
public class CreateDocThread implements Runnable {

    Thread t;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public CreateDocThread(ObjectOutputStream output, ObjectInputStream input) {
        this.objectInputStream = input;
        this.objectOutputStream = output;
        t = new Thread(this);
    }

    @Override
    public void run() {
        try {
            //Receive create doc information
            CreateDocPackage message = (CreateDocPackage) objectInputStream.readObject();

            String result = MyBus.createNewDocument(message.idOwner, message.title);                        

            if (!result.isEmpty()) {
                //Create listening port
                ServerSocket server = new ServerSocket(0);
                int port = server.getLocalPort();
                
                Global.documentPort.put(result, port);

                //Create workingThread with this port
                WorkingServerThread workingServerThread = new WorkingServerThread(server);
                workingServerThread.run();

                //Return the working port to client
                objectOutputStream.writeInt(port);
                objectOutputStream.flush();
            } else {
                objectOutputStream.writeInt(-1);
                objectOutputStream.flush();
            }
            
            objectInputStream.close();
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CreateDocThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
