/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.Global;
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
public class OpenDocThread implements Runnable{
    
    Thread t;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    
    public OpenDocThread(ObjectOutputStream output, ObjectInputStream input){
        this.objectInputStream = input;
        this.objectOutputStream = output;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            //Receive doc id to open
            String docID = (String)objectInputStream.readUTF();
            System.out.println(docID);
            
            if(Global.documentPort.containsKey(docID) == false){
                //open usable port
                ServerSocket docSocket = new ServerSocket(0);
                int port = docSocket.getLocalPort();
                
                
                //add docId and port to hashmap
                Global.documentPort.put(docID, port);
                
                //listening to clients on another thread
                WorkingServerThread openServer = new WorkingServerThread(docSocket);
                               
                
                //send port back to client in order to connect
                System.out.println(port);
                objectOutputStream.writeInt(port);
                objectOutputStream.flush();
            } else {
                //get port if is working
                int port = (int)Global.documentPort.get(docID);
                
                //send port back to client in order to connect
                System.out.println(port);
                objectOutputStream.writeInt(port);
                objectOutputStream.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(OpenDocThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
