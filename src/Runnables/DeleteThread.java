/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.MyBus;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tung
 */
class DeleteThread implements Runnable {
    
    Thread t;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public DeleteThread(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.oos = objectOutputStream;
        this.ois = objectInputStream;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            String docCode = ois.readUTF();
            
            Boolean result = MyBus.deleteDocument(docCode);
            
            oos.writeBoolean(result);
            
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(DeleteThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
