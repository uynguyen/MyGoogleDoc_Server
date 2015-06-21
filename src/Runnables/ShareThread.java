/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.MyBus;
import CommunicatePackage.ReplyInvitePackage;
import CommunicatePackage.SharePackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tung
 */
public class ShareThread implements Runnable{
    
    Thread t;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    
    public ShareThread(ObjectOutputStream output, ObjectInputStream input){
        this.objectInputStream = input;
        this.objectOutputStream = output;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            SharePackage sharePackage = (SharePackage) objectInputStream.readObject();
            
            boolean result = Bus.MyBus.shareDocument(sharePackage.docCode, sharePackage.idClient, sharePackage.username);
            
            objectOutputStream.writeBoolean(result);
            objectOutputStream.flush();
            
            objectInputStream.close();
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ReplyInviteThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
