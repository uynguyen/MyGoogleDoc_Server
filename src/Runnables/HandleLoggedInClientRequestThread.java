/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Runnables;

import Bus.Global;
import Bus.NotificationPusher;
import Pojo.EnumUserAction;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thanh Tung
 */
public class HandleLoggedInClientRequestThread implements Runnable{
    Thread t;
    Socket client;
    
    public HandleLoggedInClientRequestThread(Socket client){
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
            
            //flag create new doc or connect to an existing doc
            int flag = objectInputStream.readInt();
            
            if(flag == EnumUserAction.CREATEDOC.getValue()){
                CreateDocThread createDocThread = new CreateDocThread(objectOutputStream, objectInputStream);                
            } else if(flag == EnumUserAction.OPENDOC.getValue()){
                OpenDocThread openDocThread = new OpenDocThread(objectOutputStream, objectInputStream);
            } else if(flag == EnumUserAction.REPLYINVITE.getValue()){
                ReplyInviteThread replyInviteThread = new ReplyInviteThread(objectOutputStream, objectInputStream);
            } else if(flag == EnumUserAction.SHARE.getValue()){
                ShareThread shareThread = new ShareThread(objectOutputStream, objectInputStream, Global.notificationPusher);
            } else if(flag == EnumUserAction.DELETE.getValue()){
                DeleteThread deleteThread = new DeleteThread(objectOutputStream, objectInputStream);
            } else if(flag == EnumUserAction.LEAVE.getValue()){
                LeaveThread leaveThread = new LeaveThread(objectOutputStream, objectInputStream);
            } else if(flag == EnumUserAction.REGISTER_PUSH_SERVICE.getValue()){
                String username = objectInputStream.readUTF();
                System.out.println(username + " is now online");
                Global.notificationPusher.Register(objectOutputStream, objectInputStream, username);
            } else if(flag == EnumUserAction.WORKING.getValue()){
                String username = objectInputStream.readUTF();
                System.out.println(username + " is now working");
                Global.notificationPusher.Unregister(username);
            } else {
                String username = objectInputStream.readUTF();
                System.out.println(username + " is now offline");
                Global.notificationPusher.Unregister(username);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(HandleClientRequestThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
