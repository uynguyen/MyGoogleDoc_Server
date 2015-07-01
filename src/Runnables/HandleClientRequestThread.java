/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Pojo.EnumUserAction;
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
public class HandleClientRequestThread implements Runnable{
    Thread t;
    Socket client;

    public HandleClientRequestThread(Socket client){
        this.client = client;

        t  = new Thread(this);    
        t.start();
    }
    @Override
    public void run() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectOutputStream.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            
            int flag = objectInputStream.readInt();
            
            if(flag == EnumUserAction.LOGIN.getValue()){
                LogInThread logInThread = new LogInThread(objectOutputStream, objectInputStream);
            } else if (flag == EnumUserAction.REGISTER.getValue()){
                RegisterThread registerThread = new RegisterThread( objectOutputStream, objectInputStream);
            } else {
                ResetPasswordThread resetPasswordThread = new ResetPasswordThread(objectOutputStream, objectInputStream);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(HandleClientRequestThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
