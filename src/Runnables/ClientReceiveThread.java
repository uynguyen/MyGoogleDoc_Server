/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import java.io.ObjectInputStream;

/**
 *
 * @author ThanhTung
 */
public class ClientReceiveThread implements Runnable{
    
    Thread t;
    ObjectInputStream objectInputStream;
    Notifier notifier;
    
    public ClientReceiveThread(ObjectInputStream is, Notifier notifier){
        objectInputStream = is;        
        this.notifier = notifier;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        
    }
    
}
