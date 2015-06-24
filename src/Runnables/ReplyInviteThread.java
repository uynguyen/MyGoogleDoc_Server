/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.MyBus;
import CommunicatePackage.ReplyInvitePackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tung
 */
public class ReplyInviteThread implements Runnable {

    Thread t;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public ReplyInviteThread(ObjectOutputStream output, ObjectInputStream input) {
        this.objectInputStream = input;
        this.objectOutputStream = output;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            ReplyInvitePackage replyInvitePackage = (ReplyInvitePackage) objectInputStream.readObject();

            boolean result = false;
            if (replyInvitePackage._reply) {
                result = MyBus.acceptInvite(replyInvitePackage._idInvite, replyInvitePackage._idClient, replyInvitePackage._docCode);
            } else{
                result = MyBus.rejectInvite(replyInvitePackage._idInvite);
            }
            
            objectOutputStream.writeBoolean(result);
            objectOutputStream.flush();
            
            objectInputStream.close();
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(ReplyInviteThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReplyInviteThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
