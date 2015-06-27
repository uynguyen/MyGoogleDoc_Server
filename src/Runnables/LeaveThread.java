/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.MyBus;
import CommunicatePackage.LeavePackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tung
 */
class LeaveThread implements Runnable{
    
    Thread t;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public LeaveThread(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.oos = objectOutputStream;
        this.ois = objectInputStream;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            LeavePackage leavePackage = (LeavePackage)ois.readObject();
            
            boolean result = MyBus.leaveDocument(leavePackage.clientID, leavePackage.docCode);
            
            oos.writeBoolean(result);
            oos.flush();
            oos.close();
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LeaveThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
