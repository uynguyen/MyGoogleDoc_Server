/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Thanh Tung
 */
public class OpenExistingDocThread implements Runnable{
    
    Thread t;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    
    public OpenExistingDocThread(ObjectOutputStream output, ObjectInputStream input){
        this.objectInputStream = input;
        this.objectOutputStream = output;
        t = new Thread(this);
    }

    @Override
    public void run() {
        
    }
    
}
