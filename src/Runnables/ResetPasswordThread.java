/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tung
 */
public class ResetPasswordThread implements Runnable{
    
    Thread t;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    
    public ResetPasswordThread(ObjectOutputStream oos, ObjectInputStream ois){
        this.objectInputStream = ois;
        this.objectOutputStream = oos;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            //get username
            String username = objectInputStream.readUTF();
            
            String result = Bus.MyBus.resetPass(username);
            
            //send back result
            objectOutputStream.writeUTF(result);
            objectOutputStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(ResetPasswordThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
