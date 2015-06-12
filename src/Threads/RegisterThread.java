/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Bus.MyBus;
import CommunicatePackage.RegisterPackage;
import Pojo.Account;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThanhTung
 */
public class RegisterThread implements Runnable {

    Thread t = null;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public RegisterThread(Socket client, ObjectOutputStream output, ObjectInputStream input) {
        objectInputStream = input;
        objectOutputStream = output;
        t = new Thread(this);
    }

    @Override
    public void run() {
        try {                        
            RegisterPackage message = (RegisterPackage)objectInputStream.readObject();
            
            Account newAccount = new Account();
            newAccount.setUsername(message.username);
            newAccount.setEMail(message.email);
            newAccount.setAvatar("Images/default_avatar.jpg");
            
            boolean result = MyBus.register(newAccount, message.password);
            if(result == true){
                objectOutputStream.writeBoolean(true);
                objectOutputStream.flush();
            } else {
                objectOutputStream.writeBoolean(false);
                objectOutputStream.flush();
            }
            
            objectInputStream.close();
            objectOutputStream.close();
            
        } catch (IOException ex) {
            Logger.getLogger(LogInThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
